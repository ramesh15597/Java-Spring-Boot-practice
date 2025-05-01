package com.sai.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PurchaseRequest {

    @NotNull(message = "Product is mandatory")
    private Integer id;
    @Positive(message = "Quantity is mandatory")
    private double quantity;


}
