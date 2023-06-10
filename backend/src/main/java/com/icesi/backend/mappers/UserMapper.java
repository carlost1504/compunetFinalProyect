package com.icesi.backend.mappers;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.models.ShopUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "password", target = "password")
    ShopUser fromUserCreateDTO(UserCreateDTO userCreateDTO);

    ShopUser fromUserUpdateDTO(UserUpdateDTO userUpdateDTO);

    ShopUser fromDTO(UserDTO userDTO);

    UserDTO fromUser(ShopUser user);

    ShopUser fromDTO(UserCreateDTO userCreateDTO);

    default String fromUUID(UUID uuid) {
        return uuid.toString();
    }

    default UUID fromUUID(String uuid) {
        return UUID.fromString(uuid);
    }
}
