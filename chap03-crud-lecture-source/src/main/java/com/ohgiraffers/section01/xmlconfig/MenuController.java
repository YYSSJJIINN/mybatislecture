package com.ohgiraffers.section01.xmlconfig;

import java.util.List;
import java.util.Map;

public class MenuController {

    //필드 위치
    private final MenuService menuService;
    private final PrintResult printResult;
    //final 때문에 초기화 해줘야 한다.

    //기본생성자가 호출될 때, new 생성자와 함께 초기화된다.
    public MenuController() {
        menuService = new MenuService();
        printResult = new PrintResult();
    }


    public void selectAllMenu() {

        List<MenuDTO> menuList = menuService.selectAllMenu();

        //메뉴리스트가 null(값 없음)이 아니라면 최소한 성공은 한 것이다
        if(menuList != null) {
            printResult.printMenuList(menuList);
        } else {
            printResult.printErrorMessage("selectList");
        }
    }

    public void selectMenuByCode(Map<String, String> parameter) {

        int code = Integer.parseInt(parameter.get("code"));

        //selectMenuByCodes는 조회인지라, n개는 안되고, 1개와 0개는 가능하므로 null로 해준다.
        MenuDTO foundMenu = menuService.selectMenuByCode(code);

        if(foundMenu != null) {
            printResult.printMenu(foundMenu);
        } else {
            printResult.printErrorMessage("selectOne");
        }
    }

    public void registMenu(Map<String, String> parameter) {

        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO newMenu = new MenuDTO();

        newMenu.setName(name);
        newMenu.setPrice(price);
        newMenu.setCategoryCode(categoryCode);

        if(menuService.registMenu(newMenu)) {
            printResult.printSuccessMessage("insert");
        } else {
            printResult.printErrorMessage("insert");
        }
    }

    public void modifyMenu(Map<String, String> parameter) {

        int code = Integer.parseInt(parameter.get("code"));
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menuToUpdate = new MenuDTO();
        menuToUpdate.setCode(code);
        menuToUpdate.setName(name);
        menuToUpdate.setPrice(price);
        menuToUpdate.setCategoryCode(categoryCode);

        if(menuService.modifyMenu(menuToUpdate)) {
            printResult.printSuccessMessage("update");
        } else {
            printResult.printErrorMessage("update");
        }
    }

    public void deleteMenu(Map<String, String> parameter) {

        int code = Integer.parseInt(parameter.get("code"));

        if(menuService.deleteMenu(code)) {
            printResult.printSuccessMessage("delete");
        } else {
            printResult.printErrorMessage("delete");
        }

    }
}
