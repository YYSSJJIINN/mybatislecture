package com.ohgiraffers.section02.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Application {

    public static void main(String[] args) {

        //resources 디렉토리 경로를 기준으로, MyBatis Configuration XML 파일 위치를 표현한다.
        String resourcePath = "mybatis-config.xml";

        try {
            InputStream inputStream = Resources.getResourceAsStream(resourcePath);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(false);

            //Mapper에 등록된 쿼리를 가져오기 위해서는 mapper의 namespace에 설정된 네임스페이스와
            //쿼리 id의 조합으로 참조해야 한다. (XML 파일)
            Date curdate = sqlSession.selectOne("your-mapper.selectSysdate");
            System.out.println("curdate = " + curdate);

            int healthCheck = sqlSession.selectOne("your-mapper");
            System.out.println("healthCheck = " + healthCheck);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


