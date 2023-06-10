package com.icesi.backend.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID itemId;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;


    @PrePersist
    public void generateId() {
        this.itemId = UUID.randomUUID();
    }
}
