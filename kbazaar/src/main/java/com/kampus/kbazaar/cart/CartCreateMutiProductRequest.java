package com.kampus.kbazaar.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartCreateMutiProductRequest {
    List<CartProductRequest> cartProductRequestList;
}

