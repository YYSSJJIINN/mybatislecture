package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

public class MenuService {

    //메뉴 서비스는 메뉴DAO가 필요하다. 이것을 의존관계라고 한다.
    private final MenuDAO menuDAO;
    //기본생성자가 호출될 때 초기화가 된다.


    public MenuService() {
        menuDAO = new MenuDAO();
    }

    public List<MenuDTO> selectAllMenu() {

        //1. SqlSession 객체 생성
//        Connection conn = getConnection();
        SqlSession sqlSession = getSqlSession();

        //2. DAO 메서드 호출하여 쿼리 결과 반환 받기
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);
        //3. SqlSession 객체 반납
        sqlSession.close();

        //4. 비즈니스 로직 수행 결과 반납
        return menuList;
    }

    public MenuDTO selectMenuByCode(int code) {

        SqlSession sqlSession = getSqlSession();

        MenuDTO foundMenu = menuDAO.selectMenuByCode(sqlSession, code);
        //int code에서 입력된 숫자가 selectMenuByCode()안에 들어온다.

        sqlSession.close();

        return foundMenu;
    }

    public boolean registMenu(MenuDTO newMenu) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.registMenu(sqlSession, newMenu);

        //TCL(Transaction Control language)
        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0? true : false;
        //result가 성공해서 1이 나오면 true를 반환하고 실패해서 0이 나오면 false를 반환
    }

    public boolean modifyMenu(MenuDTO menuToUpdate) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.modifyMenu(sqlSession, menuToUpdate);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0 ? true : false;
    }

    public boolean deleteMenu(int code) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.deleteMenu(sqlSession, code);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0 ? true : false;
    }
}
