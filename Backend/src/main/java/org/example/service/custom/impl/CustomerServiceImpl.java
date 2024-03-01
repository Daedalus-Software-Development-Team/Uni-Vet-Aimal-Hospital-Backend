package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.dto.CustomerDto;
import org.example.dto.EmailDto;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.example.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CustomerEntity save(CustomerDto dto) {
        if(!validateEmail(dto)){
            return null;
        }

        CustomerEntity entity=
                mapper.convertValue(dto,CustomerEntity.class);
        return customerRepository.save(entity);
    }



    @Override
    public boolean delete(Long value) {
        Optional<CustomerEntity> customerEntityOptional
                =customerRepository.findById(value);
        if(customerEntityOptional.isPresent()){
            customerRepository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<CustomerDto> getAll() {
        EmailDto e=new EmailDto("thiwankar2003@gmail.com","UNI_vet Org","Hello","ER_DIAGRAM_ICM105_ThiwankaReiss.pdf");
        EmailDto j=new EmailDto("thiwankar2003@gmail.com","Test","Hello",null);
        e.sendEmail();
        j.sendEmail();
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

        List<CustomerDto> list=new ArrayList<>();

        Iterable<CustomerEntity> customerList=customerRepository.findAll();
        Iterator<CustomerEntity> iterator=customerList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            CustomerEntity entity=iterator.next();

            lastId= entity.getCustomerId();

        }
        return lastId+1;
    }

    private boolean validateEmail(CustomerDto dto){
        String subject="Welcome to Uni-Vet Pet Care";
        String textContent="Dear "+dto.getFirstName()+" "+dto.getLastName()+" ,\n"+"We warmly welcome you to our veterinary service.";
        EmailDto email=new EmailDto(dto.getEmail(), subject,textContent,null);

       return email.sendEmail();
    }
}
