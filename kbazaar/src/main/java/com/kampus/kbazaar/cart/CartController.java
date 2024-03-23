package com.kampus.kbazaar.cart;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public ResponseEntity getCart() { // NOSONAR
        return ResponseEntity.ok().build();
    }



    @Operation(summary = "add item to carts")
    @PostMapping("/carts/{username}/items")
    public ResponseEntity<CartResponse> createCart(
            @PathVariable(name = "username") String userName,
            @RequestBody CartRequest cartRequest){

        CartResponse cartResponse = this.cartService.createCart(userName,cartRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartResponse);
    }


}
