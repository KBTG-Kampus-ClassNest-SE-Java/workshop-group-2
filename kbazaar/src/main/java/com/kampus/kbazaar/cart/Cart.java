package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.product.Product;
import com.kampus.kbazaar.shopper.Shopper;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @OneToOne
//    @JoinColumn(name = "shopperid", referencedColumnName = "id")
//    private Shopper shopper;
//
//    @OneToMany
//    @JoinColumn(name = "productid", referencedColumnName = "id")
//    private Product[] products;
//
//    @Column(name = "quantity")
//    private int quantity;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "shopperid")
    private Long shopperId;

    @Column(name = "productid")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;


}

