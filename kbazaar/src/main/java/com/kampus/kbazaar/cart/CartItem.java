package com.kampus.kbazaar.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private String sku;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal finalPrice;

}
