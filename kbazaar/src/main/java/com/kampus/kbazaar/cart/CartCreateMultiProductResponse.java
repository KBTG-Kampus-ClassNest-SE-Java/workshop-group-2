package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import lombok.Data;

@Data
public class CartCreateMultiProductResponse {
    private String userName;
    private Product[] products;
}
