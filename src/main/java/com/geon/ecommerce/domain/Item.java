package com.geon.ecommerce.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import java.time.LocalDate;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private LocalDate releaseDate;
    private int availableUnits;
    private Point location;
    private boolean active;

    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
