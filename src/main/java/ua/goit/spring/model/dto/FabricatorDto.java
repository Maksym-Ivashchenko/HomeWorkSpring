package ua.goit.spring.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class FabricatorDto {
    private final UUID id;
    private final String name;
    private final Set<ProductDto> products;
}
