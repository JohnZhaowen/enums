package com.example.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SexEnum implements BaseEnum<SexEnum, String> {

    MALE("01", "MALE", "男性"),
    FEMALE("02", "FEMALE", "女性"),
    ;

    private String code;

    private String name;

    private String ename;

    SexEnum (String code, String name, String ename){

        this.code = code;
        this.name = name;
        this.ename = ename;
    }

    public static SexEnum parseByCode(String code) {
        return Arrays.stream(SexEnum.values()).filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }
}
