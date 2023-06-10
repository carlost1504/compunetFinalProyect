package com.icesi.backend.service.impl;

import com.icesi.backend.DTO.OrderItemDTO;
import com.icesi.backend.error.exception.EShopError;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.mappers.OrderMapper;
import com.icesi.backend.models.Item;
import com.icesi.backend.models.Order;
import com.icesi.backend.models.OrderItem;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.respositories.ItemRepository;
import com.icesi.backend.respositories.OrderRepository;
import com.icesi.backend.respositories.UserRepository;
import com.icesi.backend.service.OrderServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class OrderService implements OrderServiceInterface {

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    public final UserRepository userRepository;

    public final ItemRepository itemRepository;



    @Override
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(()-> new EShopException(HttpStatus.NOT_FOUND, new EShopError(BackendApplicationErrors.CODE_O_01, BackendApplicationErrors.CODE_O_01.getMessage())));
    }



    @Override
    @Transactional
    public Order createOrder(Order order, UUID userId, List<OrderItemDTO> items) {
        ShopUser user = userRepository.findById(userId).orElseThrow(()->
                new EShopException(HttpStatus.NOT_FOUND,
                        new EShopError(BackendApplicationErrors.CODE_O_01,
                                BackendApplicationErrors.CODE_O_01.getMessage())));

        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemDTO item : items){
            List<Item> list = itemRepository.findByAvailableAndItem(true, item.getItemId());

            if(list.size() < item.getQuantity()){
                throw new EShopException(HttpStatus.NOT_FOUND,
                        new EShopError(BackendApplicationErrors.CODE_I_02,
                                BackendApplicationErrors.CODE_I_02.getMessage()));
            }

            list = list.stream().limit(item.getQuantity()).collect(Collectors.toList());

            OrderItem orderItem = OrderItem.builder().quantity(item.getQuantity()).items(list).build();

            orderItems.add(orderItem);
        }
        //orderRepository.saveAll(orderItems);
        orderItems.forEach(it-> {
            it.getItems().forEach(item -> {
                item.setAvailable(false);
                itemRepository.updateItemAvailabilityByItemId (false, item.getItemId());
            });                                             //String description, String name, Double price, String imgUrl, String category, boolean available, UUID itemId
        });

        double total = orderItems.stream().reduce(0.0, (a, b) ->{
            Optional<Item> item = b.getItems().stream().findFirst();
            return item.map(value -> a + b.getQuantity() * value.getItemType().getPrice()).orElse(a);
        } , Double::sum);

        order.setTotal((long) total);

        order.setItemList(orderItems);

        return orderRepository.save(order);
    }




    @Override
    public void updateOrder(UUID orderId, String status) {

        int res = orderRepository.updateStatusByOrderId(status,orderId);

        if(res == 0){
            throw new EShopException(HttpStatus.NOT_FOUND, new EShopError(BackendApplicationErrors.CODE_O_01, BackendApplicationErrors.CODE_O_01.getMessage()));
        }
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public List<Order> getUserOrders(UUID userId) {
        return orderRepository.findByUser_UserId(userId);
    }




}
