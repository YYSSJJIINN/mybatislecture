package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface MyTestMapper {

    @Select("select curdate()")
    Date selectSysdate();

    //주로 Application에서 Database측과의 연결 상태를 체크해보는 용도로 1초 또는 특정 시간마다 보내는 쿼리로 주로 사용됨
    //이를 Health-check 또는 hearbeat라고 한다. 살아있는지(존재하는지) 확인하는
    @Select("select 1")
    int select1();
}
