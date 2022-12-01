package ua.goit.spring.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.UserDao;
import ua.goit.spring.model.dto.UserDto;
import ua.goit.spring.model.repository.UserRepository;
import ua.goit.spring.model.service.converter.UserConverter;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements IService<UserDto, UUID> {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        UserDao userDao = userRepository.save(userConverter.to(userDto));
        return userConverter.from(userDao);
    }

    @Override
    public UserDto getById(UUID id) {
        UserDao userDao = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return userConverter.from(userDao);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
