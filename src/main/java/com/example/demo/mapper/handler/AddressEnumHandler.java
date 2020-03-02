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

public class AddressEnumHandler implements TypeHandler<AddressEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, AddressEnum addressEnum, JdbcType jdbcType) throws SQLException {
        ps.setString(i, addressEnum.getCode());

    }

    @Override
    public AddressEnum getResult(ResultSet resultSet, String s) throws SQLException {
        return AddressEnum.parseByCode(resultSet.getString(s));
    }

    @Override
    public AddressEnum getResult(ResultSet resultSet, int i) throws SQLException {
        return AddressEnum.parseByCode(resultSet.getString(i));
    }

    @Override
    public AddressEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        return AddressEnum.parseByCode(callableStatement.getString(i));
    }
}


