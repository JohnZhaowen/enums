package com.example.demo.model;

import com.example.demo.enums.AddressEnum;
import com.example.demo.enums.SexEnum;
import com.example.demo.serializer.JsonEnumDeerializer;
import com.example.demo.serializer.JsonEnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

    private String name;

    @JsonSerialize(using = JsonEnumSerializer.class)
    @JsonDeserialize(using = JsonEnumDeerializer.class)
    private SexEnum sexEnum;

    @JsonSerialize(using = JsonEnumSerializer.class)
    @JsonDeserialize(using = JsonEnumDeerializer.class)
    private AddressEnum addressEnum;

    private String operator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
