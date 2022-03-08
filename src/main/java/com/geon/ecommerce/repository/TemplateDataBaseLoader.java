package com.geon.ecommerce.repository;

import com.geon.ecommerce.domain.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDataBaseLoader {

    @Bean
    public CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
            mongo.save(new Item("itemA", 19.99));
            mongo.save(new Item("itemB", 24.99));
        };
    }
}
