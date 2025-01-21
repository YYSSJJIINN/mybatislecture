package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section02.javaconfig.Template.getSqlSession;

public class MenuService {

    private MenuMapper menuMapper;

    public List<MenuDTO> selectAllMenu() {

        //1. SqlSession 객체 생성
//        Connection conn = getConnection();
        SqlSession sqlSession = getSqlSession();

        //2. MenuMapper(= 매퍼 인터페이스)를 취득한 후 Session을 통해 쿼리 실행 후 결과 반환 받기
        menuMapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menuList = menuMapper.selectAllMenu();

        //3. SqlSession 객체 반납
        sqlSession.close();

        //4. 비즈니스 로직 수행 결과 반납
        return menuList;
    }

    public MenuDTO selectMenuByCode(int code) {

        SqlSession sqlSession = getSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);
        MenuDTO foundMenu = menuMapper.selectMenuByCode(code);
        //int code에서 입력된 숫자가 selectMenuByCode()안에 들어온다.

        sqlSession.close();

        return foundMenu;
    }

    public boolean registMenu(MenuDTO newMenu) {

        SqlSession sqlSession = getSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);
        int result = menuMapper.registMenu(newMenu);

        //TCL(Transaction Control language)
        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0 ? true : false;
        //result가 성공해서 1이 나오면 true를 반환하고 실패해서 0이 나오면 false를 반환
    }

    public boolean modifyMenu(MenuDTO menuToUpdate) {

        SqlSession sqlSession = getSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);
        int result = menuMapper.modifyMenu(menuToUpdate);

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

        menuMapper = sqlSession.getMapper(MenuMapper.class);
        int result = menuMapper.deleteMenu(code);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0 ? true : false;
    }
}
