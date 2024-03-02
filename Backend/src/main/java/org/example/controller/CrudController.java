package org.example.controller;


import org.example.common.AuditTime;
import org.example.dto.Response;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

public interface CrudController<T,U> {

    T saveOrUpdate(U dto) ;

    public List<U> getAll() throws InterruptedException;


    Response delete(Long value) ;

    public  Long getNextId() throws Exception;
    U getById(Long id);
}
