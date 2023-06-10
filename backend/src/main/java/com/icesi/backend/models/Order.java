package com.icesi.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Builder
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    @ManyToOne
    private ShopUser shopUser;
    private String status;
    private Long total;
    private UUID userId;
    @OneToMany()
    private List<OrderItem> itemList;

}
