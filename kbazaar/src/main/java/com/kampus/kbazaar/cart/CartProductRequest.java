package com.kampus.kbazaar.cart;

import lombok.Data;

@Data
public class CartProductRequest {
    private String sku;
    private Integer qty;
}
