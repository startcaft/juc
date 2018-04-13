/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/10 15:29
 * Description: 数据字典服务
 */
package com.startcaft.basic.service;

import com.startcaft.basic.core.beans.DicItemBean;
import com.startcaft.basic.core.beans.DicItemModifyBean;
import com.startcaft.basic.core.entity.DicItem;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.DicItemVo;

import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据字典服务〉
 *
 * @author StartCaft
 * @create 2018/4/10
 * @since 1.0.0
 */
public interface IDicItemService {

    /**
     * 获取所有的顶层数据字典，pid=0
     * @return
     * @throws BasicProException
     */
    Set<DicItemVo> getTopDicItems() throws BasicProException;

    /**
     * 获取指定节点的树状结构，一般是顶层节点
     * @param id 数据字典id
     * @return
     * @throws BasicProException
     */
    DicItem getTreeByPid(long id) throws BasicProException;

    /**
     * 获取数据字典完整的树
     * @return
     * @throws BasicProException
     */
    Set<DicItem> getTree() throws BasicProException;

    /**
     * 保存数据字典
     * @param bean
     * @throws BasicProException
     */
    void saveDicItem(DicItemBean bean) throws BasicProException;

    /**
     * 修改数据字典
     * @param bean
     * @throws BasicProException
     */
    void modifyDicItem(DicItemModifyBean bean) throws BasicProException;

    /**
     * 获取指定数据字典的详细信息
     * @param id
     * @return
     * @throws BasicProException
     */
    DicItemVo getDicItemDetail(long id) throws BasicProException;
}
