package com.example.kassa3.service.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReadWriteService<T, K> {
    T persist(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteByIdCascadeEnable(K id);

    void deleteByIdCascadeIgnore(K id);

    boolean existsById(K id);

    T findById(K id);

    List<T> findAll();

    Page<T> getAll (Pageable pageable);
}
