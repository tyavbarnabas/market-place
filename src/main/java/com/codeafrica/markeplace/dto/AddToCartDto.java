package com.codeafrica.markeplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AddToCartDto {
    private Integer id;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ",productId+" +productId +
                ",quantity+" +quantity +
                ",";

    }
}
