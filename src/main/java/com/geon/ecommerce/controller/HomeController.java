package com.geon.ecommerce.controller;

import com.geon.ecommerce.domain.Cart;
import com.geon.ecommerce.repository.CartRepository;
import com.geon.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    @GetMapping
    public Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                                  .modelAttribute("items", itemRepository.findAll())
                                  .modelAttribute("cart", cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                                  .build());
    }
}
