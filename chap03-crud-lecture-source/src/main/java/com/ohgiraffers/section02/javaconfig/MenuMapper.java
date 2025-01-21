package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MenuMapper {

    @Results(id = "menuResultMap", value = {
            @Result(id = true, property = "code", column = "menu_code"),
            @Result(property = "name", column = "menu_name"),
            @Result(property = "price", column = "menu_price"),
            @Result(property = "categoryCode", column = "category_code"),
            @Result(property = "orderableStatus", column = "orderable_status")
    })

    @Select("select " +
            " m.menu_code," +
            " m.menu_name," +
            " m.menu_price," +
            " m.category_code," +
            " m.orderable_status " +
            "from tbl_menu m " +
            "where m.orderable_status = 'Y' " +
            "order by m.menu_code")
    List<MenuDTO> selectAllMenu();

    @Select("SELECT " +
            "    m.menu_code, " +
            "    m.menu_name, " +
            "    m.menu_price, " +
            "    m.category_code, " +
            "    m.orderable_status " +
            "FROM tbl_menu m " +
            "WHERE m.orderable_status = 'Y' " +
            "  AND m.menu_code = #{code}")
    /* 설명. 역시나 위에서 사용한 resultMap을 재사용 가능하다. */
    @ResultMap("menuResultMap")
    MenuDTO selectMenuByCode(int code);

    @Insert("INSERT INTO tbl_menu (" +
            "    menu_name, " +
            "    menu_price, " +
            "    category_code, " +
            "    orderable_status" +
            ") VALUES (" +
            "    #{name}, " +
            "    #{price}, " +
            "    #{categoryCode}, " +
            "    'Y'" +
            ")")
    int registMenu(MenuDTO menu);

    @Update("UPDATE tbl_menu m " +
            "SET " +
            "    m.menu_name = #{name}, " +
            "    m.menu_price = #{price}, " +
            "    m.category_code = #{categoryCode} " +
            "WHERE m.menu_code = #{code}")
    int modifyMenu(MenuDTO menu);

    @Delete("DELETE FROM tbl_menu m " +
            "WHERE m.menu_code = #{code}")
    int deleteMenu(int code);
}
