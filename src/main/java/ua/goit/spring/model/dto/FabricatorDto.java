package ua.goit.spring.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FabricatorDto {
    private UUID id;
    private String name;
}
