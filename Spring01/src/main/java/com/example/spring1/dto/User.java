package com.example.spring1.dto;

import lombok.Data;

import java.util.Map;

@Data
public class User {
    private String name;
    private Boolean gender;
    private Integer age;
    private Bike bike;
    private String[] alias;
    private Map<String, Object> test;


}
