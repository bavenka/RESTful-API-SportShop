package com.test.service.Impl;

import com.test.model.dto.OrderDto;
import com.test.model.dto.ProductListDto;
import com.test.model.entity.Order;
import com.test.model.entity.Product;
import com.test.model.entity.StatusType;
import com.test.model.entity.User;
import com.test.repository.OrderRepository;
import com.test.repository.UserRepository;
import com.test.service.OrderService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pavel on 30.11.2016.
 */
@Component
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto makeOrder(@NonNull Set<ProductListDto> productDtos, Long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Set<Product> products = user.getCartProducts();
        if (products.isEmpty()) {
            throw new Exception(Constant.MESSAGE_EMPTY_CART);
        }
        Set<Product> existingProducts = new HashSet<>();
        for (ProductListDto productListDto : productDtos) {
            for (Product product : products) {
                if (product.getId().equals(productListDto.getId())) {
                    existingProducts.add(product);
                }
            }
        }
        Order order = new Order();
        order.setDate(new Date());
        order.setStatus(StatusType.AWAIT);
        order.setOrderProducts(existingProducts);
        order.setUser(user);
        orderRepository.save(order);
        products.removeAll(existingProducts);
        user.setCartProducts(products);
        userRepository.save(user);
        return Converter.toOrderDto(order);
    }

    @Override
    public void payOrder(Long userId, Long orderId) throws Exception {
        Double orderCost = 0.0;
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_ORDER);
        }
        if (!order.getUser().getId().equals(userId)) {
            throw new Exception("User has not this order!");
        }
        Set<Product> products = order.getOrderProducts();
        for (Product product : products) {
            orderCost += product.getPrice();
        }
        if (orderCost > user.getBalance()) {
            throw new Exception("User has not so money!");
        }
        user.setBalance(user.getBalance() - orderCost);
        order.setStatus(StatusType.PAID);
        userRepository.save(user);
        orderRepository.save(order);
    }

    @Override
    public Set<OrderDto> getOrders(Long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        if (user.getOrders() == null) {
            return null;
        }
        Set<OrderDto> orderDtos = new HashSet<>();
        Set<Order> orders = user.getOrders();
        for (Order order : orders) {
            orderDtos.add(Converter.toOrderDto(order));
        }
        return orderDtos;
    }

    @Override
    public OrderDto getOrder(Long userId, Long orderId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_ORDER);
        }
        Set<Order> orders = user.getOrders();
        if (orders == null) {
            return null;
        }
        for (Order existingOrder : orders) {
            if (existingOrder.getId().equals(orderId)) {
                return Converter.toOrderDto(existingOrder);
            }
        }
        return null;
    }

    @Override
    public void cancelOrder(Long userId, Long orderId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Set<Order> orders = user.getOrders();
        if (orders == null) {
            throw new Exception("User has not orders!");
        }
        Order searchedOrder = null;
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                searchedOrder = order;
                break;
            }
        }
        if (searchedOrder == null) {
            throw new Exception("User has not this order!");
        }
        orders.remove(searchedOrder);
        user.setOrders(orders);
        userRepository.save(user);
    }

}
