package com.ohgiraffers.section03.remix;

import java.util.List;

public interface MenuMapper {

    List<MenuDTO> selectAllMenu();
    //(괄호)안에 든 것들이 파라미터. 비어있다면 파라미터가 없는 것이다.
    MenuDTO selectMenuByCode(int code);

    int registMenu(MenuDTO menu);

    int modifyMenu(MenuDTO menu);

    int deleteMenu(int code);
}
