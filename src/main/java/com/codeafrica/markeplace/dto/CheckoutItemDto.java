package com.codeafrica.markeplace.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CheckoutItemDto {
    private String productName;
    private int quantity;
    private Double price;
    private long productId;


}
