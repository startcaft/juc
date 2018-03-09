package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.DicItem;

import java.util.Set;

/**
 *
 * @author startcaft
 * @date 2018/3/2
 */
public interface IDicTypeDao {

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

    /**
     * 获取指定 pid 下的所有子节点，pid不能为空
     * @param pid 父节点id
     * @return
     * @throws Exception
     */
    Set<DicItem> selectRecursive(Long pid) throws Exception;
}
