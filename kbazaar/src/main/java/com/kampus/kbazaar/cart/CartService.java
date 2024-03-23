package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.exceptions.InternalServerException;
import com.kampus.kbazaar.exceptions.NotFoundException;
import com.kampus.kbazaar.product.Product;
import com.kampus.kbazaar.product.ProductRepository;
import com.kampus.kbazaar.shopper.Shopper;
import com.kampus.kbazaar.shopper.ShopperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ShopperRepository shopperRepository;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ShopperRepository shopperRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.shopperRepository = shopperRepository;
        this.productRepository = productRepository;
    }


    public CartResponse createCart(String userName,CartRequest cartRequest){

        Optional<Shopper> optionalShopper = this.shopperRepository.findByUsername(userName);

        if (!optionalShopper.isPresent()) {
           throw  new NotFoundException("username not found");
        }

        Optional<Product> optionalProduct = this.productRepository.findBySku(cartRequest.getSku());
        if (!optionalProduct.isPresent()) {
            throw  new NotFoundException("sku not found");
        }

        Product[] productArray = optionalProduct.map(product -> new Product[]{product})
                .orElse(new Product[0]);

        Cart cart = new Cart();
//        cart.setShopper(optionalShopper.get());
//        cart.setProducts(productArray);

        cart.setShopperId(optionalShopper.get().getId());
        cart.setProductId(optionalProduct.get().getId());
        cart.setQuantity(cartRequest.getQty());

        Cart cartDb = null;

        try {
            cartDb = this.cartRepository.save(cart);
        }catch (RuntimeException e){
            throw new InternalServerException(e.getMessage());
        }catch(Exception e){
            throw new InternalServerException("Create cart failed");
        }

        CartResponse cartResponse = new CartResponse();
        cartResponse.setUserName(userName);
        cartResponse.setCart(cartDb);
        return cartResponse;
    }

}
