package ua.goit.spring.model.service.converter;

import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.UserDao;
import ua.goit.spring.model.dto.UserDto;

@Service
public class UserConverter implements Converter<UserDto, UserDao> {
    @Override
    public UserDto from(UserDao entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setLogin(entity.getLogin());
        userDto.setPassword(entity.getPassword());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        return userDto;
    }

    @Override
    public UserDao to(UserDto entity) {
        UserDao userDao = new UserDao();
        userDao.setId(entity.getId());
        userDao.setLogin(entity.getLogin());
        userDao.setPassword(entity.getPassword());
        userDao.setFirstName(entity.getFirstName());
        userDao.setLastName(entity.getLastName());
        return userDao;
    }
}
