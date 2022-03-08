package com.geon.ecommerce.repository;

import com.geon.ecommerce.domain.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
