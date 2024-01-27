package org.example.service;

import org.example.service.custom.impl.*;
import org.example.service.utill.ServiceType;

public class ServiceFactory {
    private  static  ServiceFactory serviceFactory;
    private ServiceFactory(){

    }
    public static ServiceFactory getInstance(){
        return serviceFactory!=null? serviceFactory:(serviceFactory=new ServiceFactory());
    }

    public <T extends SuperService>T getDao(ServiceType type){
        switch (type){
            case APOINTMENT:return (T)new ApointmentServiceImpl();
            case CUSTOMER:return (T)new CustomerServiceImpl();
            case DOCTOR:return (T)new DoctorServiceImpl();
            case OTHEREXPENSE:return (T)new OtherExpenseServiceImpl();
            case PETDETAIL:return (T)new PetDetailServiceImpl();
            case PET:return (T)new PetServiceImpl();
            case PRESCRIPTION:return (T)new PrescriptionServiceImpl();
            case PRESCRIPTIONDETAIL:return (T)new PrescriptionDetailServiceImpl();
            case STAFF:return (T)new StaffServiceImpl();
        }
        return null;
    }
}
