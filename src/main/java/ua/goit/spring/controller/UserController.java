package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.spring.model.dao.UserDao;
import ua.goit.spring.model.dto.UserDto;
import ua.goit.spring.model.service.UserService;
import ua.goit.spring.model.service.converter.UserConverter;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/list")
    public List<UserDto> list() {
        return userService.findAll()
                .stream()
                .map(userConverter::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/save")
    public UserDto save(UserDto userDto) {
        UserDao userDao = userService.save(userConverter.to(userDto));
        return userConverter.from(userDao);
    }
}
