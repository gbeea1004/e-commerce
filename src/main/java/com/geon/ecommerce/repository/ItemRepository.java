package com.geon.ecommerce.repository;

import com.geon.ecommerce.domain.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
