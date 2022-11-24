package ua.goit.spring.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.UserDao;
import ua.goit.spring.model.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements ua.goit.spring.model.service.Service<UserDao, UUID> {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<UserDao> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDao save(UserDao user) {
        return userRepository.save(user);
    }

    @Override
    public UserDao getById(UUID id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(UserDao user) {
        userRepository.delete(user);
    }
}
