package com.test.service;

import com.test.model.dto.OrderDto;
import com.test.model.dto.ProductListDto;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Pavel on 30.11.2016.
 */
@Service
public interface OrderService {
    OrderDto makeOrder(Set<ProductListDto> productDtos, Long userId) throws Exception;

    void payOrder(Long userId, Long orderId) throws Exception;

    Set<OrderDto> getOrders(Long userId) throws Exception;

    OrderDto getOrder(Long userId, Long orderId) throws Exception;

    void cancelOrder(Long userId, Long orderId) throws Exception;
}
