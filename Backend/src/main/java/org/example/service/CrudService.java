package org.example.service;

import org.example.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<U,T>  {
    U save (T dto) ;
    boolean delete(Long value) ;
    List<T> getAll() ;
    Long getNextId() throws SQLException;
    T getById(Long id);
}
