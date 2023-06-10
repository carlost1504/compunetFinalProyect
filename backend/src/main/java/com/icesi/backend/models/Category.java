package com.icesi.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID categoryId;
    private String name;
    private String description;
}
