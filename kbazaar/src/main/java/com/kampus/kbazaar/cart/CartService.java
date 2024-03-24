package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.exceptions.BadRequestException;
import com.kampus.kbazaar.exceptions.NotFoundException;
import com.kampus.kbazaar.promotion.ApplyCodeRequest;
import com.kampus.kbazaar.promotion.Promotion;
import com.kampus.kbazaar.promotion.PromotionRepository;
import com.kampus.kbazaar.shopper.Shopper;
import com.kampus.kbazaar.shopper.ShopperRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final PromotionRepository promotionRepository;
    private final ShopperRepository shopperRepository;

    private final CartRepository cartRepository;

    public CartService(
            PromotionRepository promotionRepository,
            ShopperRepository shopperRepository,
            CartRepository cartRepository) {
        this.promotionRepository = promotionRepository;
        this.shopperRepository = shopperRepository;
        this.cartRepository = cartRepository;
    }

    // story_6
    public ApplyCodeResponse applyCode(String username, ApplyCodeRequest request) {
        Optional<Shopper> shopper = shopperRepository.findByUsername(username);
        if (shopper.isPresent()) {
            Optional<Promotion> promotion = promotionRepository.findByCode(request.getCode());

            if (promotion.isPresent()) {
                if (promotion.get().getProductSkus().isBlank()) {
                    return new ApplyCodeResponse();
                }

                boolean skuMatch =
                        request.getProductSkus().stream()
                                .anyMatch(x -> promotion.get().getProductSkus().contains(x));
                if (skuMatch) {
                    return new ApplyCodeResponse();
                }
                throw new BadRequestException("Cannot use discount code.");
            } else {
                throw new NotFoundException("Promotion not found");
            }
        } else {
            throw new BadRequestException("Username not found");
        }
    }

    public CreateCartResponse createCart(String userName, CartRequest cartRequest) {
        Optional<Shopper> shopper = shopperRepository.findByUsername(userName);
        if (shopper.isPresent()) {

            CreateCartResponse response = new CreateCartResponse();
            Cart cart = new Cart();
            cart.setShopperId(shopper.get().getId());
            cart.setQuantity(cartRequest.getQty());
            cartRepository.save(cart);

            response.setCartId(cart.getId());
            return response;
        } else {
            throw new BadRequestException("Create cart failed");
        }
    }
}
