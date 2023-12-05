package com.example.shoeEcommerceSpringboot.service;

import java.util.List;

public interface IGeneralService<E, T> {
    List<E> findAll();
    E findById(T id);
    void update(E e);
    void create(E e);
    Boolean delete(T id);
}
