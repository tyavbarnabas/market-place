package com.codeafrica.markeplace.dto;

import com.codeafrica.markeplace.model.Cart;
import com.codeafrica.markeplace.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Integer id;
    private Product product;
    private Integer quantity;

    public CartItemDto(Cart cart){
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());

    }
}
