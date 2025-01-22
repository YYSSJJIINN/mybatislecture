package com.ohgiraffers.section03.remix;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Template {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menudb";
    private static String USER = "root";
    private static String PASSWORD = "qwer1234";

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {

        System.out.println("0. SqlSessionFactory 인스턴스 만듭니다~");

        if(sqlSessionFactory == null) {

            Environment env = new Environment("dev",
                    new JdbcTransactionFactory(),
                    new PooledDataSource(DRIVER, URL, USER, PASSWORD));

            Configuration config = new Configuration(env);
            config.getTypeAliasRegistry().registerAlias("MenuDTO", MenuDTO.class);
            config.addMapper(MenuMapper.class); //인터페이스 클래스로 생성이다.

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        }

        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        System.out.println("1. sqlSessionFactory의 hashCode() : " + sqlSessionFactory.hashCode());
        System.out.println("2. sqlSession의 hashCode() : " + sqlSessionFactory.hashCode());

        return sqlSession;
    }
}
