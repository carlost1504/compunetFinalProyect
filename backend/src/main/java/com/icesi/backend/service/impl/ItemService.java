package com.icesi.backend.service.impl;

import com.icesi.backend.DTO.ItemCreateDTO;
import com.icesi.backend.error.EshopErrorCode;
import com.icesi.backend.error.exception.EShopError;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.error.exception.ErrorDetail;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.models.Item;
import com.icesi.backend.models.ItemType;
import com.icesi.backend.respositories.ItemRepository;
import com.icesi.backend.respositories.ItemTypeRepository;
import com.icesi.backend.service.ItemServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ItemService implements ItemServiceInterface {
    private final ItemRepository itemRepository;
    private final ItemTypeRepository itemTypeRepository;

    @Override
    public ItemType getItem(UUID id) {
        return itemTypeRepository.findById(id).orElseThrow(()-> new RuntimeException(""));
    }

    @Override
    public List<ItemType> getAllItemTypes() {
        return StreamSupport.stream(itemTypeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public boolean updateItem(ItemType itemType, UUID id) {
        int result = itemTypeRepository.updateNameAndDescriptionAndPriceAndImageByItemTypeId(itemType.getName(), itemType.getDescription(), itemType.getPrice(), itemType.getImage(), id);
        if (result == 0) {
            throw new RuntimeException("Item type not found");
        }
        return true;
    }

    @Override
    public ItemType createItem(ItemType itemDTO) {
        return itemTypeRepository.save(itemDTO);
    }

    @Override
    public List<Item> addItemToStock(UUID itemTypeId, int quantity) {

        ItemType itemType = itemTypeRepository.findById(itemTypeId).orElseThrow(() -> new RuntimeException("Item type not found"));

        for (int i = 0; i < quantity; i++) {
            Item item = Item.builder().itemId(UUID.randomUUID()).available(true).itemType(itemType).build();
            itemRepository.save(item);
        }


        return StreamSupport.stream(itemRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
