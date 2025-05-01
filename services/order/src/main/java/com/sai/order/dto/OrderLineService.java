package com.sai.order.dto;

import com.sai.order.model.Order;
import com.sai.order.model.OrderLine;
import com.sai.order.repository.OrderLineRepository;
import com.sai.order.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public Integer saveOrderLine(OrderLineRequest request) {


        return orderLineRepository.save(OrderLine.builder()

                        .id(request.id)
                        .quantity(request.quantity)
                        .order(
                                Order.builder()
                                        .id(request.orderId)
                                        .build()
                        )
                .build()).getId();


    }
}
