package com.icesi.backend.respositories;

import com.icesi.backend.models.Category;
import com.icesi.backend.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findByAvailableAndItem(boolean available, UUID itemId);

    @Transactional
    @Modifying
    @Query("update Item i set i.available = ?1 where i.itemId = ?2")
    void updateItemAvailabilityByItemId(boolean available, UUID itemId);

}
