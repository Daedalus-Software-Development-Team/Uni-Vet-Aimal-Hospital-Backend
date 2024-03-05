package org.example.service;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<U,T>  {
    U save (T dto) ;
    boolean delete(Long value) ;
    List<T> getAll() ;
    Long getNextId() throws SQLException;
    T getById(Long id);
}
