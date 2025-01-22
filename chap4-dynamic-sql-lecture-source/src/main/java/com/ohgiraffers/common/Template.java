package com.ohgiraffers.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    /* 필기. SqlSessionFactory의 생명 주기:
    * SqlSessionFactory는 애플리케이션을 실행하는 동안 내내 존재해야 하며,
    * 애플리케이션이 실행되는 동안 여러 차례 SqlSessionFactory를 다시 빌드하지 않는 것이 가장 좋은 형태다.
    * '애플리케이션 스코프'로 관리하기 위한 가장 간단한 방법은 '싱클턴 패턴'을 이용하는 것이다.
    * */
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {

        System.out.println("0. SqlSessionFactory 인스턴스 만듭니다~");

        /* 필기. SqlSessionFactoryBuilder의 생명 주기:
        * SqlSessionFactoryBuilder를 SqlSession을 생성한 후에도 유지할 필요는 없다.
        * (주 임무가 SqlSessionFactory를 짓기만 하면 되기 때문에 공장 건설 후 빠지면 된다.)
        * 따라서 '메서드 스코프'로 만든다.
        * 여러개의 SqlSessionFactory를 빌드하기 위해 재사용할 상황도 있겠지만,
        * 왠만해서는 한 번 Factory를 빌드한 후에 유지하지 않는 것이 가장 좋다.
        * */
        if(sqlSessionFactory == null) {

            String resourcesPath = "config/mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(resourcesPath);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        SqlSession sqlSession = sqlSessionFactory.openSession(false);
//        return sqlSession;
        //위의 두 문장을 아래와 같이 한 줄로 바꿀 수 있다.
        return sqlSessionFactory.openSession(false);
    }
}
