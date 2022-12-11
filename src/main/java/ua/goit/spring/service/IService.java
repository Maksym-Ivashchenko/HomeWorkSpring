package ua.goit.spring.service;

import java.util.List;

public interface IService<E,I> {
    List<E> findAll();
    E save(E entity);
    E getById(I id);
    void deleteById(I id);
}