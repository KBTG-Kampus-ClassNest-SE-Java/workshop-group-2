package com.kampus.kbazaar.cart;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartRequest {
    @NotNull
    private String sku;

    @Min(value = 1, message = "QTY must be greater than or equal to 0")
    private Integer qty;
}
