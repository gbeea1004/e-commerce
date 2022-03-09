package com.geon.ecommerce.service;

import com.geon.ecommerce.domain.Item;
import com.geon.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final ReactiveFluentMongoOperations fluentOperations;

    /**
     * Example 쿼리
     */
    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = createMatcher(useAnd);

        Example<Item> probe = Example.of(item, matcher);

        return itemRepository.findAll(probe);
    }

    /**
     * 평문형 연산
     */
    public Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = createMatcher(useAnd);

        return fluentOperations.query(Item.class)
                               .matching(query(byExample(Example.of(item, matcher))))
                               .all();
    }

    private ExampleMatcher createMatcher(boolean useAnd) {
        return (useAnd ? matchingAll() : matchingAny())
                .withStringMatcher(StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price", "availableUnits", "active");
    }
}
