package com.example.parser.service;

import java.util.List;

public interface CrudService<T> {

    List<T> getAll();
    T findByNumber(String number);
    T save (T t);
    void delete(Long id);




}
