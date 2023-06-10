package com.icesi.backend.mappers;


import com.icesi.backend.DTO.ItemTypeDTO;
import com.icesi.backend.models.ItemType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemTypeMapper {
    ItemTypeDTO fromItem(ItemType itemType);
    ItemType fromDTO(ItemTypeDTO itemTypeDTO);
}
