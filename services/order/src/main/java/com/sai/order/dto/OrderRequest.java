package com.sai.order.dto;

import com.sai.order.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Integer id;
    private String reference;
    @Positive(message = "order amount should be positive")
    private BigDecimal amount;
    @Positive(message = "paymentMethod should be precised")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Customer should be present")
    @NotEmpty(message = "Customer should be present")
    @NotBlank(message = "Customer should be present")

    private String customerId;
    @NotEmpty(message = "you should at least purchase one product")
    private List<PurchaseRequest> products;

}
