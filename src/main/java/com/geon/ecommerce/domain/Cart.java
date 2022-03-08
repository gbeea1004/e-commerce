package com.geon.ecommerce.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cart {

    @Id
    private String id;
    private List<CartItem> cartItems;

    public Cart(String id) {
        this(id, new ArrayList<>());
    }

    public Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }
}
