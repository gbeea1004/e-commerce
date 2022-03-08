package com.geon.ecommerce.domain;

import lombok.*;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItem {

    private Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }
}
