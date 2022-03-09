package com.geon.ecommerce.controller;

import com.geon.ecommerce.domain.Cart;
import com.geon.ecommerce.domain.CartType;
import com.geon.ecommerce.repository.CartRepository;
import com.geon.ecommerce.repository.ItemRepository;
import com.geon.ecommerce.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final InventoryService inventoryService;


    @GetMapping
    public Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                                  .modelAttribute("items", itemRepository.findAll())
                                  .modelAttribute("cart", cartRepository.findById("My Cart")
                                                                        .defaultIfEmpty(new Cart("My Cart")))
                                  .build());
    }

    @GetMapping("/search")
    public Mono<Rendering> search(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) boolean useAnd) {
        return Mono.just(Rendering.view("home.html")
                                  .modelAttribute("items", inventoryService.searchByExample(name, description, useAnd))
                                  .modelAttribute("cart", cartRepository.findById(CartType.DEFAULT_CART_ID.getId())
                                                                        .defaultIfEmpty(new Cart(CartType.DEFAULT_CART_ID.getId())))
                                  .build());
    }
}
