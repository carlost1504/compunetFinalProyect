package com.icesi.backend.respositories;

import com.icesi.backend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Transactional
    @Modifying
    @Query("update Order o set o.status = ?1 where o.orderId = ?2")
    int updateStatusByOrderId(String status, UUID orderId);

    List<Order> findByUser_UserId(UUID userId);

}
