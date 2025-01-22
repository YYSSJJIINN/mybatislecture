package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {

    private DynamicSqlMapper mapper;

    public void selectMenuByPrice(int price) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        /* Java 기본자료형으로는 <if> 조건문으로 값을 비교할 수 없다.
        * 또한 HashMap의 Key 또는 DTO의 getter를 이용해 값을 확인하도록 한다.
        * */
        Map<String, Integer> map = new HashMap<>();
        map.put("price", price);

        //다중행이므로 List타입이다.
        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);

        /* 검색 결과로 성공 여부 판단 */
        //menuList가 널값이 아니면서 값이 비어있지 않다면
        if (menuList != null && !menuList.isEmpty()) {
            System.out.println("입력하신 금액대 " + price + "원에 적합한 추천 메뉴 목록입니다:");
            for (MenuDTO m : menuList) {
                System.out.println(m);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }
        sqlSession.close();
    }

    public void searchMenu(SearchCriteria searchCriteria) {
        //다중행 결과가 나올 것이다. 그러므로 List<>사용

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);

        /* 검색 결과로 성공 여부 판단 */
        if (menuList != null && !menuList.isEmpty()) {
            System.out.println(searchCriteria.getCondition() + "조건으로" + searchCriteria.getValue() + "을(를) 검색한 결과입니다:");
            for (MenuDTO m : menuList) {
                System.out.println(m);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }
        sqlSession.close();
    }

    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuBySupCategory(searchCriteria);

        if(menuList != null && !menuList.isEmpty()) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }
        sqlSession.close();
    }

    public void searchMenuByRandomMenuCode(List<Integer> randomMeunuCodeList) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        Map<String, List<Integer>> criteria = new HashMap<>();
        criteria.put("randomMenuCodeList", randomMeunuCodeList);
        List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode(criteria);

        if(menuList != null && !menuList.isEmpty()) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByCodeOrSearchAll(searchCriteria);

        if(menuList != null && !menuList.isEmpty()) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuByNameOrCategory(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(criteria);

        if(menuList != null && !menuList.isEmpty()) {
            for(MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void modifyMenu(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        int result = mapper.modifyMenu(criteria);

        if(result > 0) {
            System.out.println("메뉴 정보 변경에 성공하셨습니다.");
            sqlSession.commit();
        } else {
            System.out.println("메뉴 정보 변경에 실패하셨습니다.");
            sqlSession.rollback();
        }

        sqlSession.close();
    }
}
