package com.pandacreep.onlineshop.domain.product;

import com.pandacreep.onlineshop.domain.category.Category;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "products")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String image;

    @Column
    private int qty;

    @Column(length = 128)
    private String description;

    @Column
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
