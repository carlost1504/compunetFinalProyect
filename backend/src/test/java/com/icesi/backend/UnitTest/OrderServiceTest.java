package com.icesi.backend.UnitTest;

import com.icesi.backend.DTO.OrderItemDTO;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.mappers.OrderMapper;
import com.icesi.backend.models.Item;
import com.icesi.backend.models.Order;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.respositories.ItemRepository;
import com.icesi.backend.respositories.OrderRepository;
import com.icesi.backend.respositories.UserRepository;
import com.icesi.backend.service.impl.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrder_Success() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        Order order = new Order();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        Order result = orderService.getOrder(orderId);

        // Assert
        Assertions.assertEquals(order, result);
    }

    @Test
    public void testGetOrder_OrderNotFound() {
        // Arrange
        UUID orderId = UUID.randomUUID();

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(EShopException.class, () -> orderService.getOrder(orderId));
    }

    @Test
    public void testCreateOrder_Success() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        List<OrderItemDTO> items = new ArrayList<>();

        Order order = new Order();
        order.setOrderId(orderId);

        ShopUser user = new ShopUser();
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        Order result = orderService.createOrder(order, userId, items);

        // Assert
        Assertions.assertEquals(order, result);
    }

    @Test
    public void testCreateOrder_UserNotFound() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        List<OrderItemDTO> items = new ArrayList<>();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(EShopException.class, () -> orderService.createOrder(new Order(), userId, items));
    }

    @Test
    public void testCreateOrder_InsufficientItems() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        List<OrderItemDTO> items = new ArrayList<>();
        OrderItemDTO item = new OrderItemDTO();
        item.setItemId(UUID.randomUUID());
        item.setQuantity(5);
        items.add(item);

        ShopUser user = new ShopUser();
        user.setUserId(userId);

        Item availableItem = new Item();
        availableItem.setAvailable(true);
        availableItem.setItemId(item.getItemId());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(itemRepository.findByAvailableAndItem(true, item.getItemId())).thenReturn(Collections.singletonList(availableItem));

        // Act and Assert
        Assertions.assertThrows(EShopException.class, () -> orderService.createOrder(new Order(), userId, items));
    }

    @Test
    public void testUpdateOrder_Success() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        String status = "Completed";

        when(orderRepository.updateStatusByOrderId(status, orderId)).thenReturn(1);

        // Act
        Assertions.assertDoesNotThrow(() -> orderService.updateOrder(orderId, status));
    }

    @Test
    public void testUpdateOrder_OrderNotFound() {
        // Arrange
        UUID orderId = UUID.randomUUID();
        String status = "Completed";

        when(orderRepository.updateStatusByOrderId(status, orderId)).thenReturn(0);

        // Act and Assert
        Assertions.assertThrows(EShopException.class, () -> orderService.updateOrder(orderId, status));
    }

    // Add more test cases for other methods if needed
}
