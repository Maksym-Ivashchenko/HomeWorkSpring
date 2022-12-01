package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.spring.model.dto.UserDto;
import ua.goit.spring.model.service.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("userList");
        return result.addObject("users", userService.findAll());
    }

    @GetMapping("/save")
    public ModelAndView saveForm() {
        return new ModelAndView("userSave");
    }

    @PostMapping("/save")
    public synchronized ModelAndView save(@RequestParam(name = "login") String login,
                                          @RequestParam(name = "password") String password,
                                          @RequestParam(name = "firstName") String firstName,
                                          @RequestParam(name = "lastName") String lastName) {
        ModelAndView result = new ModelAndView("userSave");
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        try {
            if (userDto.getFirstName().isBlank() || userDto.getLastName().isBlank() ||
                    userDto.getLogin().isBlank() || userDto.getPassword().isBlank()) {
                return result.addObject("message", "User not created");
            } else {
                return result.addObject("user", userService.save(userDto));
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "User not created");
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
            if (id == null || id.equals("")) {
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
            if (id == null || id.equals("")) {
                return result.addObject("message", "User not found");
            } else {
                userService.deleteById(UUID.fromString(id));
                return result.addObject("message", "User delete success");
            }
        } catch (IllegalArgumentException | IncorrectResultSizeDataAccessException e) {
            return result.addObject("message", "User not found");
        }
    }
}
