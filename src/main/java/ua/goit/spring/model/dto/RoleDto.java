package ua.goit.spring.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RoleDto {
    private final UUID id;
    private final String name;
}
