package ua.goit.spring.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
public class RoleDto {
    private final UUID id;
    private final String name;
    private final UUID userId;
}
