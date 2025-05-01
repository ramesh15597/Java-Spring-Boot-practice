package com.sai.order.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${spring.application.config.customer_url}"

)
public interface CustomerClient {
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findByCustId(@PathVariable("customer-id") String customerId);


}
