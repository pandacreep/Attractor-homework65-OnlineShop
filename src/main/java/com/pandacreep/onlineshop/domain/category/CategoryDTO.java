package com.pandacreep.onlineshop.domain.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private int id;
    private String name;

    public static CategoryDTO from(Category category) {
        return builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
