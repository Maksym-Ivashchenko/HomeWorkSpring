package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.spring.model.dto.RoleDto;
import ua.goit.spring.model.dto.UserDto;
import ua.goit.spring.service.RoleService;
import ua.goit.spring.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("userList");
        return result.addObject("users", userService.findAll());
    }

    @GetMapping("/save")
    public ModelAndView saveForm() {
        ModelAndView result = new ModelAndView("userSave");
        return result.addObject("listRoles", roleService.findAll());
    }


    @PostMapping("/save")
    public synchronized ModelAndView save(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "roleId") String userRolesId
    ) {
        ModelAndView result = new ModelAndView("userSave");
        Set<RoleDto> rolesFromDB = roleService.findAll();
        result.addObject("listRoles", rolesFromDB);
        UUID roleId = UUID.fromString(userRolesId);
        UserDto userDto = new UserDto();
        Set<RoleDto> roles = new HashSet<>();
        String message = "User not created";
        if (rolesFromDB.contains(roleService.getById(roleId))) {
            RoleDto roleDto = roleService.getById(UUID.fromString(userRolesId));
            roles.add(roleDto);
        } else {
            return result.addObject("message", message);
        }
        try {
            if (!id.isEmpty()) {
                userDto.setId(UUID.fromString(id));
            }
            userDto.setLogin(login);
            userDto.setPassword(password);
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userDto.setRoles(roles);
            if (userDto.getFirstName().isBlank() || userDto.getLastName().isBlank() ||
                    userDto.getLogin().isBlank() || userDto.getPassword().isBlank()) {
                return result.addObject("message", message);
            } else {
                result.addObject("user", userService.save(userDto));
                return result.addObject("message", "User Successfully created");
            }
        } catch (Exception e) {
            return result.addObject("message", message);
        }
    }

    @GetMapping("/search")
    public ModelAndView getByIdForm() {
        return new ModelAndView("userGetById");
    }

    @PostMapping("/search")
    public ModelAndView getById(@RequestParam(name = "userId") String id) {
        ModelAndView result = new ModelAndView("userGetById");
        try {
            if (id.isEmpty() || id.isBlank()) {
                return result.addObject("message", "User not found");
            } else {
                return result.addObject("user", userService.getById(UUID.fromString(id)));
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "User not found");
        }
    }

    @GetMapping("/delete")
    public ModelAndView deleteByIdForm() {
        return new ModelAndView("userDelete");
    }

    @PostMapping("/delete")
    public ModelAndView deleteById(@RequestParam("userId") String id) {
        ModelAndView result = new ModelAndView("userDelete");
        try {
            if (id.isEmpty() || id.isBlank()) {
                return result.addObject("message", "User not found");
            } else {
                userService.deleteById(UUID.fromString(id));
                return result.addObject("message", "User successfully deleted");
            }
        } catch (IllegalArgumentException | IncorrectResultSizeDataAccessException e) {
            return result.addObject("message", "User not found");
        }
    }
}
