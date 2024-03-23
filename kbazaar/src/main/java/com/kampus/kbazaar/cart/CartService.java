package com.kampus.kbazaar.cart;

import com.kampus.kbazaar.exceptions.BadRequestException;
import com.kampus.kbazaar.exceptions.NotFoundException;
import com.kampus.kbazaar.promotion.ApplyCodeRequest;
import com.kampus.kbazaar.promotion.ApplyCodeResponse;
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

    public CartService(
            PromotionRepository promotionRepository, ShopperRepository shopperRepository) {
        this.promotionRepository = promotionRepository;
        this.shopperRepository = shopperRepository;
    }

    //story_6
    public ApplyCodeResponse applyCode(String username, ApplyCodeRequest request) {
        Optional<Shopper> shopper = shopperRepository.findByUsername(username);
        if (shopper.isPresent()) {
            Optional<Promotion> promotion = promotionRepository.findByCode(request.getCode());

            if (promotion.isPresent()) {
                if (promotion.get().getProductSkus().isBlank()) {
                    return new ApplyCodeResponse(
                            request.getCode(),
                            promotion.get().getDiscountAmount(),
                            promotion.get().getMaxDiscountAmount());
                }

                for (String sku : request.getProductSkus()) {
                    if (sku.contains(promotion.get().getProductSkus())) {
                        return new ApplyCodeResponse(
                                request.getCode(),
                                promotion.get().getDiscountAmount(),
                                promotion.get().getMaxDiscountAmount());
                    }
                }
                throw new BadRequestException("Cannot use discount code.");
            } else {
                throw new NotFoundException("Promotion not found");
            }
        } else {
            throw new BadRequestException("Username not found");
        }
    }
}
