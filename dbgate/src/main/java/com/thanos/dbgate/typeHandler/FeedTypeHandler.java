package com.thanos.dbgate.typeHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangjialong on 6/14/18.
 */
public class FeedTypeHandler extends BaseTypeHandler<List<String>> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        // 使用rs.getString是tel字段是VARCHER类型
        List<String> value = new ArrayList();
        try {
            value = mapper.readValue(rs.getString(columnName), ArrayList.class);
        } catch (IOException e) {

        }
        return value;
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        List<String> value = new ArrayList();
        try {
            value = mapper.readValue(rs.getString(columnIndex), ArrayList.class);
        } catch (IOException e) {

        }
        return value;
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        List<String> value = new ArrayList();
        try {
            value = mapper.readValue(cs.getString(columnIndex), ArrayList.class);
        } catch (IOException e) {

        }
        return value;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    List<String> param, JdbcType jdbcType) throws SQLException {
        try {
            String value = mapper.writeValueAsString(param);
            ps.setString(i, value);
        } catch(Exception e) {

        }

    }
}
