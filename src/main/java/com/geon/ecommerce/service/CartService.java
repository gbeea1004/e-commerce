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
                             .defaultIfEmpty(new Cart(cartId))
                             .flatMap(cart -> cart.getCartItems()
                                                  .stream()
                                                  .filter(cartItem -> cartItem.getItem()
                                                                              .getId()
                                                                              .equals(id))
                                                  .findAny()
                                                  .map(cartItem -> {
                                                      cartItem.increment();
                                                      return Mono.just(cart);
                                                  })
                                                  .orElseGet(() -> itemRepository.findById(id)
                                                                                 .map(CartItem::new)
                                                                                 .map(cartItem -> {
                                                                                     cart.getCartItems().add(cartItem);
                                                                                     return cart;
                                                                                 })))
                             .flatMap(cartRepository::save);
    }
}
