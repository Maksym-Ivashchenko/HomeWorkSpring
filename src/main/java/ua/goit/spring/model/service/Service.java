package ua.goit.spring.model.service;

import ua.goit.spring.model.dto.UserDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

public interface Service<E,I> {
    List<E> findAll();
    E save(E entity);
    E getById(I id);
    void delete(E entity);
}
