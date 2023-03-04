package com.cairone;

import com.cairone.domain.SomeEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class App3 {

    public static void main(String[] args) {
        App3 app = new App3();
        app.run();
    }

    private ModelMapper mapper;

    public App3() {
        mapper = new ModelMapper();
        mapper.typeMap(SomeEntity.class, SomeEntity.class)
                .addMappings(e -> e.using(getConverter()).map(SomeEntity::getEntities, SomeEntity::setEntities));
    }

    public void run() {
        SomeEntity result = mapper.map(sampleData(), SomeEntity.class);
        System.out.println(result);
    }

    private Converter<HashSet<SomeEntity>, LinkedHashSet<SomeEntity>> getConverter() {
        return new AbstractConverter<HashSet<SomeEntity>, LinkedHashSet<SomeEntity>>() {

            @Override
            protected LinkedHashSet<SomeEntity> convert(HashSet<SomeEntity> source) {
                if (source == null) {
                    return null;
                }
                LinkedHashSet<SomeEntity> rv = new LinkedHashSet<>();
                source.forEach(e -> {
                    SomeEntity result = mapper.map(e, SomeEntity.class);
                    rv.add(result);
                });
                return rv;
            }
        };
    }

    private SomeEntity sampleData() {

        SomeEntity lvl2ChildC1 = SomeEntity.builder()
                .id(101L)
                .names("C1")
                .date(LocalDate.now())
                .build();

        Set<SomeEntity> someEntitiesLvl2 = new HashSet<>();
        someEntitiesLvl2.add(lvl2ChildC1);

        SomeEntity lvl1ChildB1 = SomeEntity.builder()
                .id(11L)
                .names("B1")
                .date(LocalDate.now())
                .entities(someEntitiesLvl2)
                .build();

        SomeEntity lvl1ChildB2 = SomeEntity.builder()
                .id(12L)
                .names("B2")
                .date(LocalDate.now())
                .build();

        Set<SomeEntity> someEntitiesLvl1 = new HashSet<>();
        someEntitiesLvl1.add(lvl1ChildB1);
        someEntitiesLvl1.add(lvl1ChildB2);

        SomeEntity main = SomeEntity.builder()
                .id(1L)
                .names("A1")
                .date(LocalDate.now())
                .entities(someEntitiesLvl1)
                .build();

        return main;
    }
}
