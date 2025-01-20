package com.ohgiraffers.section01.xmlconfig;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

public class Application {

    public static void main(String[] args) {

        //SqlSession 생성 요청을 여러번 날려서 각 객체의 Scope 및 Life-cycle을 알아본다.

        //1 번째 SqlSession 객체 생성 요청
        System.out.println(getSqlSession());
        //2 번째 SqlSession 객체 생성 요청
        System.out.println(getSqlSession());
        //3 번째 SqlSession 객체 생성 요청
        System.out.println(getSqlSession());
        //4 번째 SqlSession 객체 생성 요청
        System.out.println(getSqlSession());
        //5 번째 SqlSession 객체 생성 요청
        System.out.println(getSqlSession());


    }
}
