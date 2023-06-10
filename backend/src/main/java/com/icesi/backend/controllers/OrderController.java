package com.icesi.backend.controllers;

import com.icesi.backend.DTO.OrderDTO;
import com.icesi.backend.DTO.OrderUpdateDTO;
import com.icesi.backend.api.OrderAPI;
import com.icesi.backend.mappers.OrderMapper;
import com.icesi.backend.service.impl.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController implements OrderAPI {

    public final OrderService orderService;

    public  final OrderMapper orderMapper;

    @Override
    public OrderDTO getOrder(@NotNull UUID orderId) {
        return orderMapper.fromOrder(orderService.getOrder(orderId));
    }

    @Override
    public List<OrderDTO> getUserOrders(@NotNull UUID userId) {
        return orderService.getUserOrders(userId).stream().map(orderMapper::fromOrder).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderService.getOrders().stream().map(orderMapper::fromOrder).collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(@Valid OrderDTO orderDTO) {
        return orderMapper.fromOrder(orderService.createOrder(orderMapper.fromDTO(orderDTO), orderDTO.getUserId(), orderDTO.getOrderItems()));
    }

    @Override
    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {
        orderService.updateOrder(orderUpdateDTO.getOrderId(),orderUpdateDTO.getStatus().toString());
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderService.deleteOrder(orderId);
    }

}
