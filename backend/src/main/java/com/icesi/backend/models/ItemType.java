package com.icesi.backend.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "item_type")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemType {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID itemTypeId;

    private String name;

    private String description;

    private Double price;

    private String image;


    @PrePersist
    public void generateId() {
        this.itemTypeId = UUID.randomUUID();
    }
}
