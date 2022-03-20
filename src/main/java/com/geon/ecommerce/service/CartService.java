package com.geon.ecommerce.service;

import com.geon.ecommerce.domain.Cart;
import com.geon.ecommerce.domain.CartItem;
import com.geon.ecommerce.repository.CartRepository;
import com.geon.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public Mono<Cart> addToCart(String cartId, String id) {
        return cartRepository.findById(cartId)
                             .log("foundCart")
                             .defaultIfEmpty(new Cart(cartId))
                             .log("emptyCart")
                             .flatMap(cart -> cart.getCartItems()
                                                  .stream()
                                                  .filter(cartItem -> cartItem.getItem()
                                                                              .getId()
                                                                              .equals(id))
                                                  .findAny()
                                                  .map(cartItem -> {
                                                      cartItem.increment();
                                                      return Mono.just(cart).log("newCartItem");
                                                  })
                                                  .orElseGet(() -> itemRepository.findById(id)
                                                                                 .log("fetchedItem")
                                                                                 .map(CartItem::new)
                                                                                 .log("cartItem")
                                                                                 .map(cartItem -> {
                                                                                     cart.getCartItems().add(cartItem);
                                                                                     return cart;
                                                                                 }).log("addedCartItem")))
                             .log("cartWithAnotherItem")
                             .flatMap(cartRepository::save)
                             .log("savedCart");
    }
}
