package ua.goit.spring.model.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Set<RoleDto> roles;
}
