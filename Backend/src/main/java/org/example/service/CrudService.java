package org.example.service;

import org.example.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<T> extends SuperService {
    CustomerEntity save (T dto) throws SQLException, ClassNotFoundException;
    boolean delete(Long value) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
    Long getNextId() throws SQLException;
}
