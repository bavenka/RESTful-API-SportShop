package com.test.dao;

import org.springframework.stereotype.Service;

/**
 * Created by Павел on 28.07.2016.
 */
@Service
public interface GenericDao<T> {
    T save(T entity);
    T find(Long id);
    T update(T entity);
    void delete(Long id);
}
