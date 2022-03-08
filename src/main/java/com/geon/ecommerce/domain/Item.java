package com.geon.ecommerce.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {

    @Id
    private String id;
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
