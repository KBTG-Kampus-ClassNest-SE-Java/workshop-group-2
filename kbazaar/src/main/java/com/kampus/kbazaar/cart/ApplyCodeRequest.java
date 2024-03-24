package com.kampus.kbazaar.promotion;

import java.util.List;
import lombok.Data;

@Data
public class ApplyCodeRequest {
    private String code;
    private List<String> productSkus;
}
