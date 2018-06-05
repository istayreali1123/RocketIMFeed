package com.thanos.dbgate.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by istayreali on 2018/6/3.
 */
public abstract class AbstractDAO {
//todo
    @Autowired
    @Qualifier("sqlSessionFactory")
    protected SqlSessionFactory sqlSessionFactory;

    protected abstract BaseSqlSession getSqlSession();

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
