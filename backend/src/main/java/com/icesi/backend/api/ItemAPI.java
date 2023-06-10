package com.icesi.backend.api;


import com.icesi.backend.DTO.ItemTypeDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/items")
public interface ItemAPI {

    @GetMapping
    List<ItemTypeDTO> getAllItemTypes();

    @PostMapping()
    ItemTypeDTO addItemType(@RequestBody ItemTypeDTO itemTypeDTO);

    @GetMapping("/{id}")
    ItemTypeDTO getItem(@PathVariable UUID id);

    @PutMapping("/{id}")
    boolean updateItem(@RequestBody ItemTypeDTO itemTypeDTO, @PathVariable UUID id);

    @PostMapping("/{itemId}/stock")
    boolean addItemToStock(@PathVariable UUID itemId, @RequestBody int quantity);

}
