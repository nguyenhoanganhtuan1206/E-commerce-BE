package com.ecommerce.api.product.dto;

import com.ecommerce.api.category.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDTO {

    private UUID id;

    private String name;

    private double price;

    private String condition;

    private boolean productStatus;

    private String description;

    private CategoryDTO category;
}
