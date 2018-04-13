/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/10 15:32
 * Description: 数据字典服务实现累
 */
package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.beans.DicItemBean;
import com.startcaft.basic.core.beans.DicItemModifyBean;
import com.startcaft.basic.core.entity.DicItem;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.SqlExecuteException;
import com.startcaft.basic.core.vo.DicItemVo;
import com.startcaft.basic.dao.master.IDicItemDao;
import com.startcaft.basic.service.IDicItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据字典服务实现类〉
 *
 * @author StartCaft
 * @create 2018/4/10
 * @since 1.0.0
 */
@Service
public class DicItemServiceImpl implements IDicItemService {

    @Autowired
    private IDicItemDao dicItemDao;

    @Override
    public Set<DicItemVo> getTopDicItems() throws BasicProException {
        {
            // 查询添加是 pid = 0；
            Map<String,Object> params = new HashMap<>();
            params.put("pid",0);
            Set<DicItem> datas = dicItemDao.selectListDynamic(params);

            // 数据集根据id排序
            Set<DicItemVo> voSet = new TreeSet<>(new Comparator<DicItemVo>() {
                @Override
                public int compare(DicItemVo o1, DicItemVo o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
            datas.forEach((item) -> {
                voSet.add(item.copyPropertiesTemplate(new DicItemVo()));
            });

            return voSet;
        }
    }

    @Override
    public DicItem getTreeByPid(long id) throws BasicProException {
        {
            DicItem dicItem = dicItemDao.selectTreeById(id);
            return dicItem;
        }
    }

    @Override
    public Set<DicItem> getTree() throws BasicProException {
        {
            Set<DicItem> itemSet = dicItemDao.selectTree();
            return itemSet;
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void saveDicItem(DicItemBean bean) throws BasicProException {
        {
            Map<String,Object> params = new HashMap<>(1);
            params.put("name",bean.getName());

            // 确保数据字典不重复
            Set<DicItem> voSet = dicItemDao.selectListDynamic(params);
            if (voSet != null && voSet.size() > 0){
                throw new BasicProException("数据字典[" + bean.getName() + "]已经存在");
            }

            DicItem dicItem = new DicItem();
            bean.copyPropertiesTemplate(dicItem);
            // 表示添加的是根节点
            if (dicItem.getPid() == null){
                dicItem.setPid(0L);
            }

            int result = dicItemDao.insert(dicItem);
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void modifyDicItem(DicItemModifyBean bean) throws BasicProException {
        {
            if (bean.isCheckName()){
                Map<String,Object> params = new HashMap<>(1);
                params.put("name",bean.getName());

                // 确保数据字典不重复
                Set<DicItem> voSet = dicItemDao.selectListDynamic(params);
                if (voSet != null && voSet.size() > 0){
                    throw new BasicProException("数据字典[" + bean.getName() + "]已经存在");
                }
            }

            DicItem dicItem = new DicItem();
            bean.copyPropertiesTemplate(dicItem);

            int result = dicItemDao.updateByPrimaryKeySelective(dicItem);
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }

    @Override
    public DicItemVo getDicItemDetail(long id) throws BasicProException {
        {
            DicItem dicItem = dicItemDao.selectByPrimaryKey(id);
            return dicItem.copyPropertiesTemplate(new DicItemVo());
        }
    }
}
