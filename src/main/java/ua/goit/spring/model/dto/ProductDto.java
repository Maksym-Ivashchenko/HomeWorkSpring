package ua.goit.spring.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String name;
    private Long price;
    private FabricatorDto fabricatorDto;
}
