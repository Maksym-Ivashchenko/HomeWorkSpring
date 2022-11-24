package ua.goit.spring.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private final UUID id;
    private final String name;
    private final Long price;
    private final FabricatorDto fabricatorDao;
}
