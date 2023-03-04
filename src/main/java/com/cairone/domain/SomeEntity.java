package com.cairone.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class SomeEntity {

    private Long id;
    private String names;
    private LocalDate date;
    private Set<SomeEntity> entities;

    public SomeEntity() {
    }

    @Builder
    public SomeEntity(Long id, String names, LocalDate date, Set<SomeEntity> entities) {
        this.id = id;
        this.names = names;
        this.date = date;
        this.entities = entities;
    }
}
