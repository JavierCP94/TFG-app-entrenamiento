package com.example.springbackend.service;

import java.util.List;
import java.util.Optional;

/**
 * Generic service interface for basic CRUD operations
 * 
 * @param <T> Entity type
 * @param <ID> ID type
 */
public interface BaseService<T, ID> {
    
    List<T> findAll();
    
    Optional<T> findById(ID id);
    
    T save(T entity);
    
    void deleteById(ID id);
}