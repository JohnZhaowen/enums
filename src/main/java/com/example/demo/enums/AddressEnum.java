package com.example.demo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AddressEnum implements BaseEnum<AddressEnum> {

    JX("01", "jiangxi" ),
    SH("02", "shanghai" ),
    ;

    private String code;

    private String name;


    AddressEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static AddressEnum parseByCode(String code) {
        return Arrays.stream(AddressEnum.values()).filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }
}
