package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.DicItem;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author startcaft
 * @date 2018/3/2
 */
public interface IDicItemDao {

    /**
     * 添加一个字典类别
     * @param record  DicType对象
     * @return
     */
    int insert(DicItem record);

    /**
     * 查询一个字典类别详细，包含父节点
     * @param id
     * @return
     */
    DicItem selectByPrimaryKey(Long id);

    /**
     * 更新一个字典类别数据
     * @param record DicType对象
     * @return
     */
    int updateByPrimaryKeySelective(DicItem record);

//    /**
//     * 获取指定 id 的数据字典树状结构
//     * @param pid 父节点id
//     * @return
//     * @throws Exception
//     */
//    Set<DicItem> selectByPid(Long pid);

    /**
     * 获取指定 id 的数据字典树状结构
     * @param id 数据字典id
     * @return
     */
    DicItem selectTreeById(long id);

    /**
     * 获取数据字典完整树
     * @return
     */
    Set<DicItem> selectTree();

    /**
     * 动态查询数据列表
     * @param params 参数映射
     * @return
     */
    Set<DicItem> selectListDynamic(Map<String,Object> params);
}
