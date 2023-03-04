package com.cairone.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class FooEntity {

    private String id;
    private String names;
    private LocalDate birthdate;
    private Set<String> preferences;
}
