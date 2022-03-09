package com.geon.ecommerce.service;

import com.geon.ecommerce.domain.Item;
import com.geon.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.domain.ExampleMatcher.*;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final ItemRepository itemRepository;

    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd ? matchingAll() : matchingAny())
                .withStringMatcher(StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price", "availableUnits", "active");

        Example<Item> probe = Example.of(item, matcher);

        return itemRepository.findAll(probe);
    }
}