package com.icesi.backend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemTypeDTO {

    private UUID itemTypeId;

    @NotNull(message = "The name cannot be null")
    @Size(min = 3, max = 50, message = "The name must have between 3 and 50 characters")
    private String name;

    @NotNull(message = "The description cannot be null")
    @Size(min = 3, max = 50, message = "The description must have between 10 and 100 characters")
    private String description;

    @NotNull(message = "The price cannot be null")
    @Min(value = 0, message = "The price must be greater than 0")
    private double price;

    private String image;
}
