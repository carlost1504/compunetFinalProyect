package com.icesi.backend.DTO;

import com.icesi.backend.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ItemCreateDTO {
    private String description;
    private String name;
    private Long price;
    private String imgUrl;
}
