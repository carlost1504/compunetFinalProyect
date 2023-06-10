package com.icesi.backend.respositories;

import com.icesi.backend.models.ItemType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ItemTypeRepository extends CrudRepository<ItemType, UUID> {
    @Transactional
    @Modifying
    @Query("update ItemType i set i.name = ?1, i.description = ?2, i.price = ?3, i.image = ?4 where i.itemTypeId = ?5")
    int updateNameAndDescriptionAndPriceAndImageByItemTypeId(String name, String description, Double price, String image, UUID itemTypeId);

    Optional<ItemType> findByName(String name);

}
