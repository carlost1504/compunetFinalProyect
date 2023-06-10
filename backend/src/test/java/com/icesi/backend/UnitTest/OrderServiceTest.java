package com.icesi.backend.UnitTest;

import com.icesi.backend.DTO.OrderItemDTO;
import com.icesi.backend.mappers.OrderMapper;
import com.icesi.backend.models.Order;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.respositories.ItemRepository;
import com.icesi.backend.respositories.OrderRepository;
import com.icesi.backend.respositories.UserRepository;
import com.icesi.backend.service.impl.OrderService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;


public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrder_ValidOrderId_ReturnsOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        Order result = orderService.getOrder(orderId);

        // Assert
        assertNotNull(result);
        assertEquals(order, result);
        verify(orderRepository).findById(orderId);
    }

    @Test
    public void testGetOrder_InvalidOrderId_ThrowsException() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> orderService.getOrder(orderId));
        verify(orderRepository).findById(orderId);
    }

    @Test
    public void testCreateOrder_ValidData_ReturnsCreatedOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        List<OrderItemDTO> items = new ArrayList<>();
        Order order = new Order();

        ShopUser user = new ShopUser();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(itemRepository.findByAvailableAndItemId(true, any(UUID.class))).thenReturn(new ArrayList<>());
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Order result = orderService.createOrder(order, userId, items);

        // Assert
        assertNotNull(result);
        assertEquals(order, result);
        verify(userRepository).findById(userId);
        verify(itemRepository, times(items.size())).findByAvailableAndItemId(eq(true), any(UUID.class));
        verify(orderRepository).save(order);
    }



    @Test
    public void testUpdateOrder_ValidOrderIdAndStatus_NoExceptionThrown() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        String status = "Completed";
        when(orderRepository.updateStatusByOrderId(status, orderId)).thenReturn(1);

        // Act & Assert
        assertDoesNotThrow(() -> orderService.updateOrder(orderId, status));
        verify(orderRepository).updateStatusByOrderId(status, orderId);
    }

    @Test
    public void testUpdateOrder_InvalidOrderId_ThrowsException() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        String status = "Completed";
        when(orderRepository.updateStatusByOrderId(status, orderId)).thenReturn(0);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> orderService.updateOrder(orderId, status));
        verify(orderRepository).updateStatusByOrderId(status, orderId);
    }

    @Test
    public void testDeleteOrder_ValidOrderId_DeletesOrder() {
        // Arrange
        UUID orderId = UUID.randomUUID();

        // Act
        orderService.deleteOrder(orderId);

        // Assert
        verify(orderRepository).deleteById(orderId);
    }

    @Test
    public void testGetOrders_ReturnsListOfOrders() {
        // Arrange
        List<Order> orderList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderList);

        // Act
        List<Order> result = orderService.getOrders();

        // Assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findAll();
    }

    @Test
    public void testGetUserOrders_ValidUserId_ReturnsListOfOrders() {
        // Arrange
        UUID userId = UUID.randomUUID();
        List<Order> orderList = new ArrayList<>();
        when(orderRepository.findByUserId(userId)).thenReturn(orderList);

        // Act
        List<Order> result = orderService.getUserOrders(userId);

        // Assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findByUserId(userId);
    }
}


