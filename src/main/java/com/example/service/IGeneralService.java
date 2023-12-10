package com.example.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<E, T> {
    List<E> findAll();
    Optional<E> findById(T id);
    void update(E e);
    void create(E e);
    Boolean delete(T id);
}
