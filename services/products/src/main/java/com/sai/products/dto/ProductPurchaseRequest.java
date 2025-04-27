package com.sai.products.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseRequest {

    @NotNull(message = "Product is Mandatory")
    private Integer id;
    @NotNull(message = "quantity is Mandatory")
    private double quantity;

}
