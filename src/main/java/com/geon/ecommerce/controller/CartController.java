package com.geon.ecommerce.controller;

import com.geon.ecommerce.domain.CartType;
import com.geon.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;

    @PostMapping("/add/{id}")
    public Mono<String> addToCart(@PathVariable String id) {
        return cartService.addToCart(CartType.DEFAULT_CART_ID.getId(), id)
                          .thenReturn("redirect:/");
    }
}
