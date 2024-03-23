package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import lombok.Data;

@Data
public class CartResponse {
    private String userName;
    private Cart cart;

}