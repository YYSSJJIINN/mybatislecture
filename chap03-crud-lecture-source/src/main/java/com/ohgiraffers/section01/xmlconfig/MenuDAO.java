package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {

    public List<MenuDTO> selectAllMenu(SqlSession sqlSession) {

        return sqlSession.selectList("MenuMapper.selectAllMenu");
    }

    public MenuDTO selectMenuByCode(SqlSession sqlSession, int code) {

        return sqlSession.selectOne("MenuMapper.selectMenuByCode");
    }

    public int registMenu(SqlSession sqlSession, MenuDTO newMenu) {

        return sqlSession.insert("MenuMapper.registMenu", newMenu);
    }

    public int modifyMenu(SqlSession sqlSession, MenuDTO menuToUpdate) {

        return sqlSession.update("MenuMapper.modifyMenu", menuToUpdate);
    }

    public int deleteMenu(SqlSession sqlSession, int code) {

        return sqlSession.delete("MenuMapper.deleteMenu", code);
    }
}
