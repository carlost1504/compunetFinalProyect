package com.icesi.backend.service.impl;

import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.DTO.UserUpdateDTO;
import com.icesi.backend.mappers.UserMapper;
import com.icesi.backend.models.ShopUser;
import com.icesi.backend.respositories.UserRepository;
import com.icesi.backend.service.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public ShopUser getUser(){
        return (ShopUser) userRepository.findAll();
    }
    public Optional<ShopUser> createUser(UserCreateDTO user){
        Optional<String> email = Optional.ofNullable(user.getEmail());
        Optional<String> numberPhone = Optional.ofNullable(user.getPhoneNumber());

        if(numberPhone.isPresent() && email.isPresent()){
            Optional<ShopUser> existingUserByEmail = userRepository.findByEmail(user.getEmail());
            Optional<ShopUser> existingUserByPhone = userRepository.findByPhoneNumber(user.getPhoneNumber());
            if(existingUserByPhone.isPresent() || existingUserByEmail.isPresent()){
                throw new DuplicateKeyException("Email or Number Phone already exist");
            }
        }
        if(email.isPresent()){
            Optional<ShopUser> existingUserByEmail = userRepository.findByEmail(user.getEmail());

            if(existingUserByEmail.isPresent()) {
                throw new DuplicateKeyException("Email already exists");
            }
            ShopUser newShopUser = userMapper.fromUserCreateDTO(user);
            return Optional.of(userRepository.save(newShopUser));
        }
        if(numberPhone.isPresent()) {
            Optional<ShopUser> existingUserByPhone = userRepository.findByPhoneNumber(user.getPhoneNumber());

            if(existingUserByPhone.isPresent()){
                throw new DuplicateKeyException("Number phone already exists");
            }
            ShopUser newShopUser = userMapper.fromUserCreateDTO(user);
            return Optional.of(userRepository.save(newShopUser));
        }
        return Optional.empty();
    }

    public Optional<ShopUser> updateUser(UserUpdateDTO userUpdateDTO){
        return Optional.of(userRepository.save(userMapper.fromUserUpdateDTO(userUpdateDTO)));
    }
}
