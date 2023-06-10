package com.icesi.backend.service;


import com.icesi.backend.DTO.OrderItemDTO;
import com.icesi.backend.models.Order;

import java.util.List;
import java.util.UUID;

public interface OrderServiceInterface {

        public Order getOrder(UUID orderId);
        public Order createOrder(Order order, UUID userId, List<OrderItemDTO> orderItems);

        public void updateOrder(UUID orderId, String status);

        public void deleteOrder(UUID orderId);

        public List<Order> getOrders();

        public List<Order> getUserOrders(UUID userId);

}
