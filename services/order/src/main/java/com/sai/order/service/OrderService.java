package com.sai.order.service;

import com.sai.order.dto.*;
import com.sai.order.exceptionhandler.BusinessException;
import com.sai.order.model.Order;
import com.sai.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderLineService orderLineService;

    public String createOrder(OrderRequest request) {

        Optional<CustomerResponse> customerResponse = Optional.ofNullable(this.customerClient.findByCustId(request.getCustomerId()).orElseThrow(
                () -> new BusinessException("Cannot create order:: no order exists")
        ));

        this.productClient.purchaseProducts(request.getProducts());

        Order order = this.repository.save(Order.builder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .reference(request.getReference())
                .totalAmount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())

                .build());

        for (PurchaseRequest purchaseRequest : request.getProducts()) {

            orderLineService.saveOrderLine(

                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.getId(),
                            purchaseRequest.getQuantity()

                    )
            );
        }


        return null;
    }
}
