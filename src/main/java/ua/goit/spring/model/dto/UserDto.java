package ua.goit.spring.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class UserDto {
    private final UUID id;
    private final String name;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final Set<RoleDto> roles;
}
