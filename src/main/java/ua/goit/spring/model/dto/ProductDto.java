package ua.goit.spring.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
public class ProductDto {
    private final UUID id;
    private final String name;
    private final Long price;
    private final FabricatorDto fabricatorDao;
    private final UUID fabricatorId;
}
