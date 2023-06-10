package com.icesi.backend.respositories;

import com.icesi.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query("SELECT r FROM Role  r where r.roleName = :name")
    Optional<Role> findByName(String name);
}
