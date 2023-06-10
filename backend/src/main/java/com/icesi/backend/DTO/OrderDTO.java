package com.icesi.backend.DTO;


import com.icesi.backend.errorConstants.OrderStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private UUID orderId;

    private Double total;

    @NotNull(message = "The status cannot be null")
    private OrderStatus status;

    @NotNull(message = "The list of Items cannot be null")
    private List<OrderItemDTO> orderItems;

    @NotNull(message = "The user cannot be null")
    private UUID userId;
}
