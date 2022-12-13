package ua.goit.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.UserDao;
import ua.goit.spring.model.dto.UserDto;
import ua.goit.spring.repository.UserRepository;
import ua.goit.spring.service.converter.UserConverter;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements IService<UserDto, UUID> {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public Set<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::from)
                .collect(Collectors.toSet());
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
