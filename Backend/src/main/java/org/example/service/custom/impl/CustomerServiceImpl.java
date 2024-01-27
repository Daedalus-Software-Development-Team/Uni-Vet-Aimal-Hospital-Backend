package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.db.DBConnection;
import org.example.dto.CustomerDto;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.example.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    ObjectMapper mapper;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerEntity save(CustomerDto dto) throws SQLException, ClassNotFoundException {
        CustomerEntity entity=
                mapper.convertValue(dto,CustomerEntity.class);
        return customerRepository.save(entity);
    }



    @Override
    public boolean delete(Long value) throws SQLException, ClassNotFoundException {
        Optional<CustomerEntity> customerEntityOptional
                =customerRepository.findById(value);
        if(customerEntityOptional.isPresent()){
            customerRepository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        List<CustomerDto> list=new ArrayList<>();

        Iterable<CustomerEntity> customerList=customerRepository.findAll();
        Iterator<CustomerEntity> iterator=customerList.iterator();
        while(iterator.hasNext()){
            CustomerEntity entity=iterator.next();


            CustomerDto customerDto=mapper.convertValue(entity,CustomerDto.class);
            list.add(customerDto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        String sql = "SELECT * FROM customer_sequence";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getLong(1);
        }
        return null;
    }
}
