package com.cairone.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class FooDto {

    private String id;
    private String names;
    private LocalDate birthdate;
    private Integer age;
    private Set<String> preferences;
}
