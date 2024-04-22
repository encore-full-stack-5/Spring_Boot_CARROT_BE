package com.example.carrot_market.core;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyStringToListTypeHandler extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, Types.VARCHAR);
        } else {
            String join = String.join(",", parameter);
            ps.setString(i, join);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String images = rs.getString(columnName);
        return images != null ? Arrays.asList(images.split(",")) : new ArrayList<>();
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String images = rs.getString(columnIndex);
        return images != null ? Arrays.asList(images.split(",")) : new ArrayList<>();
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String images = cs.getString(columnIndex);
        return images != null ? Arrays.asList(images.split(",")) : new ArrayList<>();
    }
}