package com.kampus.kbazaar.promotion;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyCodeResponse {
    private String code;
    private BigDecimal discountAmount;
    private BigDecimal maxDiscountAmount;
}
