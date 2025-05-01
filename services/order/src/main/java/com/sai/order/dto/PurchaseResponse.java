package com.sai.order.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PurchaseResponse {


    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
