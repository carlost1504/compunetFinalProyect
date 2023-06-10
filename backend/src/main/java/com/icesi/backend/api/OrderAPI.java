package com.icesi.backend.api;


import com.icesi.backend.DTO.OrderDTO;
import com.icesi.backend.DTO.OrderUpdateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/orders")
public interface OrderAPI {
    @GetMapping("/{orderId}")
    OrderDTO getOrder(@PathVariable UUID orderId);

    @GetMapping("/all/{userId}")
    List<OrderDTO> getUserOrders(@PathVariable UUID userId);

    @GetMapping
    List<OrderDTO> getAllOrders();

    @PostMapping
    OrderDTO createOrder(@RequestBody OrderDTO orderDTO);

    @PutMapping
    void updateOrder(@RequestBody OrderUpdateDTO orderUpdateDTO);

    @DeleteMapping("/{orderId}")
    void deleteOrder(@PathVariable UUID orderId);
}
