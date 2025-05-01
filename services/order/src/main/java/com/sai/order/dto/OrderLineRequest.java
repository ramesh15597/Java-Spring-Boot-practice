package com.sai.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public class OrderLineRequest {
    Integer id;
    @NotNull(message = "Product is mandatory")
    Integer productId;

    Integer orderId;
    @Positive(message = "Quantity is mandatory")
    double quantity;


    public OrderLineRequest(Integer id, Integer productId, @NotNull(message = "Product is mandatory") Integer id1, @Positive(message = "Quantity is mandatory") double quantity) {
    }
}
