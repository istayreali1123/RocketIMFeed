package com.thanos.dbgate.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;


public class SimpleDAO extends AbstractDAO {

    protected BaseSqlSession getSqlSession() {
        SqlSession sqlSession = getSqlSessionFactory().openSession(ExecutorType.SIMPLE, true);
        return new BaseSqlSession(sqlSession);
    }
}
