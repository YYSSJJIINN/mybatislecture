package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Date;

public class Application {

    /* 클래스명과 메인메서드 사이 : 필드 적는 곳 */
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menudb";
    private static String USER = "root";
    private static String PASSWORD = "qwer1234";

    //static 안붙이면, 전달인자에 app.driver app.url이래야함

    public static void main(String[] args) {

        /* 목차. 0. 환경설정 */
        /* 목차 0-1. DB 접속을 위한 환경 설정 진행.
         * Environment : DB 접속에 관한 환경 설정 정보를 가진 객체.
         *  -------------------------------------------------------
         *  JdbcTransactionFactory : 수동 커밋
         *  ManagedTransactionFactory : 자동 커밋
         *  -------------------------------------------------------
         *  PooledDataSource : ConnectionPool 사용
         *  UnpooledDataSource : ConnectionPool 미사용
         *  -------------------------------------------------------
         * Connection Pool이란?
         * 데이터베이스 연결을 효율적으로 관리하는 기술이다.
         * 미리 연결을 생성해 풀(Pool)에 저장해두고, 재사용하는 방식으로 성능을 향상시킨다.
         * 연결 생성 및 종료 작업을 줄여 응답 시간을 단축시켜줘서 장점이 있다.
         * */
        //기본생성자 없이 매개변수 있는 생성자 만들면 괄호에 빨간선생김
        Environment env = new Environment(
                "myDev",                                            //환경설정 이름(식별자)
                new JdbcTransactionFactory(),                           //트랜잭션 매니저 종류 결정(JDBC수동 or MANAGED자동)
                new PooledDataSource(DRIVER, URL, USER, PASSWORD));    //ConnectionPool 사용유무(Pooled or UnPooled)

        /* 목차 0-2. 생성한 환경 설정 정보를 참조해서 MyBatis 설정 객체인 Configuration 생성 */
        Configuration config = new Configuration(env);   //이 괄호에 env가 들어감

        /* 목차 0-3. 설정 객체에 매퍼 위치 등록. */  //특이하게 인터페이스 형태로 만들어줘야함.
        config.addMapper(MyTestMapper.class);     //()괄호안은 위치를 적어주는데, Application과 Mapper파일의 위치가 같으므로 이름만 적어준다.

        /* 목차. 1. SqlSession 생성 */
        /* 목차. 1-1. SqlSessionFactory 객체 생성
         * SqlSessionFactory : SqlSession 객체를 생성하기 위한 팩토리 역할의 인터페이스
         *  SqlSessionFactoryBuilder : SqlSessionFactory 인터페이스 타입의 하위 구현 객체를 생성하기 위한 빌드 역할
         *  build() : 설정에 대한 정보를 담고 있는 Configuration 타입의 객체 혹은 외부 설정 파일과 연결된
         *            Stream을 매개변수로 전달하면 SqlSessionFactory 인터페이스 타입의 객체를 반환하는 메소드
         * */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);

        /* 목차. 1-2. SqlSession 객체 생성
         * openSession() : SqlSession 인터페이스 타입의 객체를 반환하는 메소드로 boolean 타입을 인자로 전달
         *  - false : Connection 인터페이스 타입 객체로 DML 수행 후 auto commit에 대한 옵션을 false로 지정 (권장)
         *  - true : Connection 인터페이스 타입 객체로 DML 수행 후 auto commit에 대한 옵션을 true로 지정
         *  */
        SqlSession sqlSession = sqlSessionFactory.openSession(false);   //일종의 Connection과 비슷한 것. 연결통로

        /* 목차. 2. Mapper에 정의된 쿼리 실행 */
        /* 목차. 2-1. SQL 쿼리가 매핑된 메서드가 선언되어 있는 Mapper 인터페이스를 취득한다. */
        MyTestMapper mapper = sqlSession.getMapper(MyTestMapper.class);

        /* 목차. 2-2.  Mapper 인터페이스에 작성된 메서드(== SQL Query)를 호출하여 쿼리 실행 */
        Date currentDate = mapper.selectSysdate();
        System.out.println("currentDate = " + currentDate);

        int select1 = mapper.select1();
        System.out.println("select1 = " + select1);

        boolean isConnected = select1 == 1 ? true : false;
        System.out.println("현재 Database와의 연결 상태 : " + isConnected);

        /* 목차. 사용한3. 사용한 자원 반납 */
        sqlSession.close();

    }
}
