package com.sai.products.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ProductPurchaseResponse {


    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
