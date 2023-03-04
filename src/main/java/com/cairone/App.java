package com.cairone;

import com.cairone.domain.FooEntity;
import com.cairone.dto.FooDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class App 
{
    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public void run() {

        // Creamos un Converter para HashSet - LinkedHashSet
        Converter<HashSet<String>, LinkedHashSet<String>> converter = new AbstractConverter<HashSet<String>, LinkedHashSet<String>>() {

            @Override
            protected LinkedHashSet<String> convert(HashSet<String> source) {
                LinkedHashSet<String> rv = new LinkedHashSet<>();
                source.forEach(rv::add);
                return rv;
            }
        };

        HashSet<String> preferences = new HashSet<>();
        preferences.add("pref-1");
        preferences.add("pref-2");

        FooEntity foo1 = new FooEntity();
        foo1.setId("abc");
        foo1.setBirthdate(LocalDate.now());
        foo1.setNames("names");
        foo1.setPreferences(preferences);

        ModelMapper mapper = new ModelMapper();

        FooDto fooDto = mapper
                .typeMap(FooEntity.class, FooDto.class)
                .addMappings(e -> e.using(converter).map(FooEntity::getPreferences, FooDto::setPreferences))
                .map(foo1);

        System.out.println(fooDto);
    }
}
