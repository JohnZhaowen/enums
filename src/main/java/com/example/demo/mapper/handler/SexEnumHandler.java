package com.example.demo.mapper.handler;

import com.example.demo.enums.AddressEnum;
import com.example.demo.enums.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SexEnumHandler implements TypeHandler<SexEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        ps.setString(i, sexEnum.getCode());

    }

    @Override
    public SexEnum getResult(ResultSet resultSet, String s) throws SQLException {
        return SexEnum.parseByCode(resultSet.getString(s));
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, int i) throws SQLException {
        return SexEnum.parseByCode(resultSet.getString(i));
    }

    @Override
    public SexEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        return SexEnum.parseByCode(callableStatement.getString(i));
    }
}


