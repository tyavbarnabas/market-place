package com.codeafrica.markeplace.dto;


import com.codeafrica.markeplace.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String imageUrL;
    @NotNull
    private double price;
    @NotNull
    private String description;
    @NotNull
    private Integer categoryId;


    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImageUrL(product.getImageUrl());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }
}
