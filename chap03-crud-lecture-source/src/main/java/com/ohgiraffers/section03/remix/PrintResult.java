package com.ohgiraffers.section03.remix;

import java.util.List;

public class PrintResult {

    public void printMenuList(List<MenuDTO> menuList) {

        for(MenuDTO m : menuList) {
            System.out.println(m);
        }
    }

    public void printMenu(MenuDTO menu) {
        System.out.println(menu);
    }

    public void printSuccessMessage(String successCode) {
        String successMessage = "";

        switch(successCode) {
            case "insert" : successMessage = "[INFO] 신규 메뉴 등록를 성공하였습니다."; break;
            case "update" : successMessage = "[INFO] 메뉴 정보 수정을 성공하였습니다."; break;
            case "delete" : successMessage = "[error] 메뉴 삭제를 성공하였습니다."; break;
        }
        System.out.println(successMessage);
    }

    //Application do안에 있는 질문 5개에게 에러가 뜰 때를 모두 적어야 하므로 5개를 적는다.
    public void printErrorMessage(String errorCode) {

        String errorMessage = "";

        switch(errorCode) {
            case "selectList" : errorMessage = "[error] 메뉴 목록 조회를 실패하였습니다."; break;
            case "selectOne" : errorMessage = "[error] 개별 메뉴 조회를 실패하였습니다."; break;
            case "insert" : errorMessage = "[error] 신규 메뉴 등록를 실패하였습니다."; break;
            case "update" : errorMessage = "[error] 메뉴 정보 수정을 실패하였습니다."; break;
            case "delete" : errorMessage = "[error] 메뉴 삭제를 실패하였습니다."; break;
            default : errorMessage = "[error] 알 수 없는 오류가 발생했습니다. 다시 시도해주세요"; break;
        }
    }
}
