package com.icesi.backend.respositories;

import com.icesi.backend.models.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ShopUser, UUID> {

    @Query("SELECT u FROM ShopUser u WHERE u.phoneNumber = :phoneNumber")
    Optional<ShopUser> findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM ShopUser u WHERE u.email = :email")
    Optional<ShopUser> findByEmail(String email);
}
