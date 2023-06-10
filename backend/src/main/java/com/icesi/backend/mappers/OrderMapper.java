package com.icesi.backend.mappers;


import com.icesi.backend.DTO.OrderDTO;
import com.icesi.backend.DTO.OrderItemDTO;
import com.icesi.backend.errorConstants.OrderStatus;
import com.icesi.backend.models.Order;
import com.icesi.backend.models.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "itemList", target = "orderItems")
    OrderDTO fromOrder(Order order);

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "orderItems", target = "itemList")
    Order fromDTO(OrderDTO orderDTO);

    @Mapping(target = "orderItemId", source = "orderItemId")
    @Mapping(target = "quantity", source = "quantity")
    OrderItem fromDTO(OrderItemDTO orderItemDTO);

    @Mapping(target = "orderItemId", source = "orderItemId")
    @Mapping(target = "quantity", source = "quantity")
    default OrderItemDTO fromOrderItem(OrderItem orderItem){
        return OrderItemDTO.builder()
                .orderItemId(orderItem.getOrderItemId())
                .quantity(orderItem.getQuantity())
                .itemId(orderItem.getItems().stream().findFirst().orElseThrow().getItemType().getItemTypeId())
                .build();
    }

    default String fromOrderStatus(OrderStatus status) {
        return status.getMessage();
    }

    default OrderStatus toOrderStatus(String status) {
        return OrderStatus.valueOf(status);
    }


}
