package com.icesi.backend.controllers;

import com.icesi.backend.DTO.ItemCreateDTO;
import com.icesi.backend.DTO.ItemTypeDTO;
import com.icesi.backend.error.exception.EShopError;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.mappers.ItemTypeMapper;
import com.icesi.backend.models.Item;
import com.icesi.backend.service.impl.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("*")
public class ItemController {

    private ItemService itemService;
    private ItemTypeMapper itemTypeMapper;

    @CrossOrigin(origins = {"localhost:3000"})
    public ItemTypeDTO getItem(UUID id) {
        return itemTypeMapper.fromItem(itemService.getItem(id));
    }




    public ItemTypeDTO addItemType(@Valid ItemTypeDTO itemTypeDTO) {

        return itemTypeMapper.fromItem(itemService.createItem(itemTypeMapper.fromDTO(itemTypeDTO)));
    }


    @CrossOrigin(origins = {"localhost:3000"})
    public boolean addItemToStock(UUID itemId, int quantity) {
        boolean isEmpty = itemService.addItemToStock(itemId, quantity).isEmpty();
        return !isEmpty;
    }

    @CrossOrigin(origins = {"localhost:3000"})
    public List<ItemTypeDTO> getAllItemTypes() {
        return itemService.getAllItemTypes().stream().map(itemTypeMapper::fromItem).collect(Collectors.toList());
    }

    @CrossOrigin(origins = {"localhost:3000"})
    public boolean updateItem(@Valid ItemTypeDTO itemTypeDTO, UUID id) {
        return itemService.updateItem(itemTypeMapper.fromDTO(itemTypeDTO), id);
    }
}
