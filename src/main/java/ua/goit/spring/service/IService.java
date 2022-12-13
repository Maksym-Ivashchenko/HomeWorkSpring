package ua.goit.spring.service;

import java.util.Set;

public interface IService<E,I> {
    Set<E> findAll();
    E save(E entity);
    E getById(I id);
    void deleteById(I id);
}
