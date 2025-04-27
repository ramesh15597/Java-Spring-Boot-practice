package com.sai.products.dto;

import com.sai.products.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder

public class ProductRequest {


    private Integer id;
    @NotNull(message = "Product name is required")
    private String name;
    @NotNull(message = "Product description is required")
    private String description;
    @NotNull(message = "Product availableQuantity is positive")
    private double availableQuantity;
    @NotNull(message = "Product price is positive")
    private BigDecimal price;
    @NotNull(message = "Product category id is required")
    private Integer category_id;

}
