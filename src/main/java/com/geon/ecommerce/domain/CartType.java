package com.geon.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CartType {

    DEFAULT_CART_ID("My Cart");

    private final String id;
}
