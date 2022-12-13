package ua.goit.spring.service.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.UserDao;
import ua.goit.spring.model.dto.UserDto;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserConverter implements Converter<UserDto, UserDao> {
    private final RoleConverter roleConverter;
    @Override
    public UserDto from(UserDao entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setLogin(entity.getLogin());
        userDto.setPassword(entity.getPassword());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setRoles(entity.getRoles().stream()
                .map(roleConverter::from)
                .collect(Collectors.toSet()));
        return userDto;
    }

    @Override
    public UserDao to(UserDto entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDao userDao = new UserDao();
        userDao.setId(entity.getId());
        userDao.setLogin(entity.getLogin());
        userDao.setPassword(encoder.encode(entity.getPassword()));
        userDao.setFirstName(entity.getFirstName());
        userDao.setLastName(entity.getLastName());
        userDao.setRoles(entity.getRoles().stream()
                .map(roleConverter::to)
                .collect(Collectors.toSet()));
        return userDao;
    }
}
