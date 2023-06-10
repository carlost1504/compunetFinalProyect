package com.icesi.backend.service;



import com.icesi.backend.models.Item;
import com.icesi.backend.models.ItemType;

import java.util.List;
import java.util.UUID;

public interface ItemServiceInterface {

    ItemType getItem(UUID id);

    List<ItemType> getAllItemTypes();

    boolean updateItem(ItemType itemType, UUID id);

    ItemType createItem(ItemType itemDTO);

    List<Item> addItemToStock(UUID itemTypeId, int quantity);

}
