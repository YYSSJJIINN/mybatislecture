package com.ohgiraffers.common;

/* 설명.
 * 해당 클래스는 검색 조건(condition)과 검색 값(Value)을 캡슐화하여
 * 동적 SQL 작성을 지원하는 일종의 데이터 전달 객체(DTO)로 설계한 클래스다.
 * */
public class SearchCriteria {

    //검색을 실행하기 위한(검색옵션) 필드명을 저장. (예: "category", "name" 등)
    private String condition;
    //사용자가 (검색창에) 입력한 검색어를 저장. (예: "음료". "민트미역국" 등)
    private String value;

    public SearchCriteria() {
    }

    public SearchCriteria(String condition, String value) {
        this.condition = condition;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "condition='" + condition + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
