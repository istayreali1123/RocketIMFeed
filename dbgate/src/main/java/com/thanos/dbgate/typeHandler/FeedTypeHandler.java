package com.thanos.dbgate.typeHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.pojo.FeedMapper;
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
public class FeedTypeHandler extends BaseTypeHandler<List<FeedMapper.Resource>> {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<FeedMapper.Resource> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        // 使用rs.getString是tel字段是VARCHER类型
        List<FeedMapper.Resource> value = new ArrayList();
        try {
            value = mapper.readValue(rs.getString(columnName), new TypeReference<List<FeedMapper.Resource>>(){});
        } catch (IOException e) {

        }
        return value;
    }

    @Override
    public List<FeedMapper.Resource> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        List<FeedMapper.Resource> value = new ArrayList();
        try {
            value = mapper.readValue(rs.getString(columnIndex), new TypeReference<List<FeedMapper.Resource>>(){});
        } catch (IOException e) {

        }
        return value;
    }

    @Override
    public List<FeedMapper.Resource> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        List<FeedMapper.Resource> value = new ArrayList();
        try {
            value = mapper.readValue(cs.getString(columnIndex), new TypeReference<List<FeedMapper.Resource>>(){});
        } catch (IOException e) {

        }
        return value;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    List<FeedMapper.Resource> param, JdbcType jdbcType) throws SQLException {
        try {
            String value = mapper.writeValueAsString(param);
            ps.setString(i, value);
        } catch(Exception e) {

        }

    }
}
