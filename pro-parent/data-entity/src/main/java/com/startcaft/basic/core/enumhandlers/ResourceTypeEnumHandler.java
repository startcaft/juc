package com.startcaft.basic.core.enumhandlers;

import com.startcaft.basic.core.enums.ResourceType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * sys_resoruce 表 resoruce_type字段 与 ResourceType枚举 之间的转换
 * @author startcaft
 */
public class ResourceTypeEnumHandler implements TypeHandler<ResourceType> {

    /**
     * 保存 ResourceType枚举的 code 值到数据库
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, ResourceType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getCode());
    }

    /**
     * 通过code获取ResourceType枚举
     */
    @Override
    public ResourceType getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return ResourceType.getResourceType(code);
    }

    @Override
    public ResourceType getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return ResourceType.getResourceType(code);
    }

    @Override
    public ResourceType getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer code = callableStatement.getInt(i);
        return ResourceType.getResourceType(code);
    }
}
