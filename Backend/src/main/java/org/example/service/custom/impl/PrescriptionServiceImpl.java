package org.example.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.example.dto.*;
import org.example.entity.MedicineEntity;
import org.example.entity.PrescriptionDetailEntity;
import org.example.entity.PrescriptionEntity;
import org.example.repository.MedicineRepository;
import org.example.repository.PrescriptionDetailRepository;
import org.example.repository.PrescriptionRepository;
import org.example.service.custom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    PrescriptionRepository repository;
    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    PrescriptionDetailRepository prescriptionDetailRepository;

    @Autowired
    PrescriptionDetailService prescriptionDetailService;

    @Autowired
    MedicineService medicineService;

    @Autowired
    DoctorService doctorService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PetService petService;




    @Override
    public PrescriptionEntity save(PrescriptionDto dto) {
        PrescriptionEntity prescriptionEntity=new PrescriptionEntity();
        prescriptionEntity.setPrescriptionId(dto.getPrescriptionId());
        prescriptionEntity.setDescription(dto.getDescription());
        prescriptionEntity.setCustomerId(dto.getCustomerId());
        prescriptionEntity.setTotal(dto.getTotal());
        prescriptionEntity.setDoctorId(dto.getDoctorId());
        prescriptionEntity.setPetId(dto.getPetId());

        PrescriptionEntity savedPrescriptionEntity =repository.save(prescriptionEntity);
        //        System.out.println(dto.getPrescriptionDetailArray());

        for (PresDetailAndMedCombinedDto dto1: dto.getPrescriptionDetailArray()) {
            MedicineEntity medicineEntity=new MedicineEntity(
                    dto1.getMedicineId(),
                    dto1.getMedicineName(),
                    Double.parseDouble(dto1.getPrice())
            );
            MedicineEntity savedMedicineEntity=medicineRepository.save(medicineEntity);


            PrescriptionDetailEntity prescriptionDetailEntity=new PrescriptionDetailEntity(
                    dto1.getPrescriptionDetailId(),
                    savedPrescriptionEntity.getPrescriptionId(),
                    savedMedicineEntity.getMedicineId(),
                    dto1.getAvailable(),
                    dto1.getBeforeMeal() ,
                    Double.parseDouble(dto1.getDailyQuantity()),
                    Integer.parseInt(dto1.getDays()),
                    Double.parseDouble(dto1.getPrice()),
                    dto1.getDosage()
            );
            prescriptionDetailRepository.save(prescriptionDetailEntity);

        }
        try {
            System.out.println(savedPrescriptionEntity.getPrescriptionId());
            generateReport(savedPrescriptionEntity.getPrescriptionId());
        }catch (Exception e){
            System.out.println(e);
        }


        return savedPrescriptionEntity;
    }

    @Override
    public boolean delete(Long value) {
        Optional<PrescriptionEntity> entityOptional
                =repository.findById(value);
        if(entityOptional.isPresent()){
            repository.deleteById(value);
            return true;
        }
        return false;
    }

    @Override
    public List<PrescriptionDto> getAll() {
        List<PrescriptionDto> list=new ArrayList<>();

        Iterable<PrescriptionEntity> entityList=repository.findAll();
        Iterator<PrescriptionEntity> iterator=entityList.iterator();
        while(iterator.hasNext()){
            PrescriptionEntity entity=iterator.next();

            PrescriptionDto dto=getById(entity.getPrescriptionId());
            list.add(dto);
        }
        return list;
    }

    @Override
    public Long getNextId() throws SQLException {
        Iterable<PrescriptionEntity> entityList = repository.findAll();
        Iterator<PrescriptionEntity> iterator = entityList.iterator();
        Long lastId=null;
        while(iterator.hasNext()){
            PrescriptionEntity entity=iterator.next();
            lastId= entity.getPrescriptionId();
        }
        return lastId+1;
    }

    @Override
    public PrescriptionDto getById(Long id) {
        Optional<PrescriptionEntity> entityPresent=repository.findById(id);

        PrescriptionDto dto =new PrescriptionDto();
        if(entityPresent.isPresent()){
          PrescriptionEntity entity = entityPresent.get();
            dto.setPrescriptionId(entity.getPrescriptionId());
            dto.setDescription(entity.getDescription());
            dto.setTotal(entity.getTotal());
            dto.setCustomerId(entity.getCustomerId());
            dto.setDoctorId(entity.getDoctorId());
            dto.setPetId(entity.getPetId());
            dto.setPrescriptionDetailArray(getDetailById(id));
        }
        return dto;
    }

    private List<PresDetailAndMedCombinedDto> getDetailById(Long id){
        List<PresDetailAndMedCombinedDto> list=new ArrayList<>();
        List<PrescriptionDetailDto> prescriptionDetailDtos=prescriptionDetailService.getAll();
        for (PrescriptionDetailDto dto:prescriptionDetailDtos) {
            if(dto.getPrescriptionId().equals(id)){
                PresDetailAndMedCombinedDto combinedDto=new PresDetailAndMedCombinedDto();
                MedicineDto medDto=medicineService.getById(dto.getMedicineId());
                combinedDto.setMedicineId(medDto.getMedicineId());
                combinedDto.setMedicineName(medDto.getMedicineName());
                combinedDto.setPrice(String.valueOf(medDto.getPrice()));
                combinedDto.setDays(String.valueOf(dto.getDays()));
                combinedDto.setAvailable(dto.getAvailable());
                combinedDto.setDosage(dto.getDosage());
                combinedDto.setBeforeMeal(dto.getBeforeMeal());
                combinedDto.setAvailable(dto.getAvailable());
                combinedDto.setPrescriptionDetailId(dto.getPrescriptionDetailId());
                combinedDto.setDailyQuantity(String.valueOf(dto.getDailyQuantity()));
                list.add(combinedDto);
            }
        }
        return list;
    }

    @Override
    public void generateReport(Long prescriptionId) throws JRException, FileNotFoundException {
        System.out.println("Came"+prescriptionId);
        PrescriptionDto prescriptionDto=getById(prescriptionId);
        DoctorDto doctorDto=doctorService.getById(prescriptionDto.getDoctorId());
        CustomerDto customerDto=customerService.getById(prescriptionDto.getCustomerId());
        PetDto petDto=petService.getById(prescriptionDto.getPetId());
        List<PresDetailAndMedCombinedDto> detailList=prescriptionDto.getPrescriptionDetailArray();

        List <PrescriptionReportMappingDto> list =new ArrayList<>();
        for (PresDetailAndMedCombinedDto dto: detailList) {
            PrescriptionReportMappingDto mapObj=new PrescriptionReportMappingDto();
            mapObj.setMedicineId(dto.getMedicineId());
            mapObj.setMedicineName(dto.getMedicineName());
            mapObj.setDosage(dto.getDosage());
            mapObj.setDailyQuantity(dto.getDailyQuantity());
            try{
                if(dto.getBeforeMeal()){
                    mapObj.setTiming("Before Meal");
                }else{
                    mapObj.setTiming("After Meal");
                }
            }catch (Exception e){
                mapObj.setTiming("Not Specified");
            }

            mapObj.setDailyQuantity(dto.getDailyQuantity());
            mapObj.setDays(dto.getDays());
            try{
                 if(dto.getAvailable()){
                    mapObj.setAvailable("Available");
                }else{
                    mapObj.setAvailable("Not Available");
                }
            }catch (Exception e){
                mapObj.setAvailable("Not Specified");
            }

            mapObj.setPrice(dto.getPrice());
            list.add(mapObj);

        }
        JRBeanCollectionDataSource itemsJBean= new JRBeanCollectionDataSource(list);

        String outputfile = "src/main/resources/reports/PdfFiles/"+prescriptionId+"report.pdf";
        JasperDesign design= JRXmlLoader.load("src/main/resources/reports/Prescription.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("PrescriptionId",""+prescriptionId);
        parameters.put("Description",""+prescriptionDto.getDescription());
        parameters.put("DoctorId",""+doctorDto.getDoctorId());
        parameters.put("DoctorName",""+doctorDto.getName());
        parameters.put("DoctorDescription",""+doctorDto.getDescription());
        parameters.put("DoctorEmail",""+doctorDto.getEmail());
        parameters.put("CustomerId",""+customerDto.getCustomerId());
        parameters.put("CustomerName",""+customerDto.getFirstName()+" "+customerDto.getLastName());
        parameters.put("PetName",""+petDto.getPetName());
        parameters.put("PetId",""+petDto.getPetId());

        parameters.put("PrescriptionDetailData",itemsJBean);

        parameters.put("Total",""+prescriptionDto.getTotal());


        JasperReport jasperReport= JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        OutputStream outputStream=new FileOutputStream(new File(outputfile));
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//        JasperReport jasperReport= JasperCompileManager.compileReport(design);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//        JasperViewer.viewReport(jasperPrint,false);

        EmailDto e=new EmailDto(customerDto.getEmail(),"Uni-Vet Prescription Delivery","Please find the prescription attached bellow",prescriptionId+"report.pdf");
        e.sendEmail();
    }
}
