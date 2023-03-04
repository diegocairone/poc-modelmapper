package com.cairone;

import com.cairone.domain.FooEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Hello world!
 *
 */
public class App2
{
    public static void main( String[] args ) {
        App2 app = new App2();
        app.run();
    }

    public void run() {

        // Creamos un Converter para HashSet - LinkedHashSet
        Converter<HashSet<String>, LinkedHashSet<String>> converter = new AbstractConverter<HashSet<String>, LinkedHashSet<String>>() {

            @Override
            protected LinkedHashSet<String> convert(HashSet<String> source) {
                if (source == null) {
                    return null;
                }
                LinkedHashSet<String> rv = new LinkedHashSet<>();
                source.forEach(rv::add);
                return rv;
            }
        };

        ModelMapper mapper = new ModelMapper();
        TypeMap<FooEntity, FooEntity> typeMap = mapper.typeMap(FooEntity.class, FooEntity.class);
        typeMap.addMappings(e -> e.using(converter).map(FooEntity::getPreferences, FooEntity::setPreferences));

        HashSet<String> preferences = new HashSet<>();
        preferences.add("pref-1");
        preferences.add("pref-2");

        FooEntity foo1 = new FooEntity();
        foo1.setId("abc");
        foo1.setBirthdate(LocalDate.now());
        foo1.setNames("names");
        foo1.setPreferences(preferences);

        FooEntity entity = mapper.map(foo1, FooEntity.class);

        System.out.println(entity);
    }
}
