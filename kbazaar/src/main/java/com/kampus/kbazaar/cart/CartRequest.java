package com.kampus.kbazaar.cart;

import lombok.Data;

@Data
public class CartRequest {
    private String sku;
    private Integer qty;
}
