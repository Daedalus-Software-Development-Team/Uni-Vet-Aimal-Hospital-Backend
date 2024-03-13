package org.example.service.custom.impl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.example.dto.*;
import org.example.service.custom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EBookServiceImpl implements EbookService {
    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    PetDetailService petDetailService;
    @Autowired
    PetService petService;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    DoctorService doctorService;

    @Autowired
    VaccineService vaccineService;

    @Override
    public List<PrescriptionDto> getPrescriptionsById(Long petId) {
        List<PrescriptionDto> list=new ArrayList<>();
        List<PrescriptionDto> all=prescriptionService.getAll();
        for (PrescriptionDto dto:all) {
            if(dto.getPetId().equals(petId)){
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public List<PetDetailDto> getPetDetailsByPetId(Long petId) {
        List<PetDetailDto> all=petDetailService.getAll();
        List<PetDetailDto> list=new ArrayList<>();
        for (PetDetailDto dto:all) {
            if(dto.getPetId().equals(petId)){
                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public String printBook(Long petId) {
        String outputFile = "src\\main\\resources\\reports\\PdfFiles\\output.pdf";



        System.out.println("PDF files merged successfully!");
        List<Long> prescriptionIds=new ArrayList<>();

        try {
            createFrontPage(petId);
            generateVaccine(petId);
            List<PrescriptionDto> allPrescriptions=prescriptionService.getAll();
            for (PrescriptionDto dto:allPrescriptions) {
                if(dto.getPetId().equals(petId)){
                    createPrescriptions(dto.getPrescriptionId());
                    prescriptionIds.add(dto.getPrescriptionId());
                }
            }


        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String> inputFiles=new ArrayList<>();
        inputFiles.add("src\\main\\resources\\reports\\PdfFiles\\Cover-Front.pdf");
        inputFiles.add("src\\main\\resources\\reports\\PdfFiles\\"+petId+"EbookVac.pdf");
        inputFiles.add("src\\main\\resources\\reports\\PdfFiles\\"+petId+"frontPage.pdf");
        for (Long id:prescriptionIds) {
            String s="src\\main\\resources\\reports\\PdfFiles\\"+id+"Ebook.pdf";
            inputFiles.add(s);
        }
        inputFiles.add("src\\main\\resources\\reports\\PdfFiles\\Cover-Back.pdf");
        mergePDFs(inputFiles, outputFile);
        PetDto pet=petService.getById(petId);
        CustomerDto cust=customerService.getById(pet.getCustomerId());
        EmailDto email=new EmailDto(cust.getEmail(),"Uni-Vet Pet Care E book","View your E book","output.pdf");
        email.sendEmail();
        return "Successful ";

    }
    private void createFrontPage(Long petId) throws JRException {
        PetDto petDto=petService.getById(petId);
        CustomerDto customerDto=customerService.getById(petDto.getCustomerId());
        String outputfile = "src/main/resources/reports/PdfFiles/"+petId+"frontPage.pdf";
        JasperDesign design= JRXmlLoader.load("src/main/resources/reports/PetFrontPage.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("petOwnerId",""+customerDto.getCustomerId());
        parameters.put("nic",""+customerDto.getNic());
        parameters.put("petOwnerName",""+customerDto.getFirstName()+" "+customerDto.getLastName());
        parameters.put("contact",""+customerDto.getContact());
        parameters.put("email",""+customerDto.getEmail());
        parameters.put("petId",""+petDto.getPetId());
        parameters.put("petName",""+petDto.getPetName());
        parameters.put("type",""+petDto.getType());
        parameters.put("genre",""+petDto.getGenre());
        parameters.put("gender",""+petDto.getGender());
        parameters.put("birthYear",""+petDto.getBirthYear());
        parameters.put("weight",""+petDto.getWeight());




        JasperReport jasperReport= JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        OutputStream outputStream= null;
        try {
            outputStream = new FileOutputStream(new File(outputfile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }

    public void createPrescriptions(Long prescriptionId) throws JRException, FileNotFoundException {

        PrescriptionDto prescriptionDto=prescriptionService.getById(prescriptionId);
        DoctorDto doctorDto=doctorService.getById(prescriptionDto.getDoctorId());


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

        String outputfile = "src/main/resources/reports/PdfFiles/"+prescriptionId+"Ebook.pdf";
        JasperDesign design= JRXmlLoader.load("src/main/resources/reports/EBookPrescription.jrxml");
        Map<String, Object> parameters = new HashMap<>();


        parameters.put("PrescriptionDetailDataSet",itemsJBean);
        parameters.put("PrescriptionId",""+prescriptionDto.getPrescriptionId());
        parameters.put("Description",""+prescriptionDto.getDescription());
        parameters.put("DoctorId",""+prescriptionDto.getDoctorId());
        parameters.put("Name",""+doctorDto.getName());
        parameters.put("doctorDescription",""+doctorDto.getDescription());
        parameters.put("Email",""+doctorDto.getEmail());
        parameters.put("Date",""+prescriptionDto.getDate());
        parameters.put("Total",""+prescriptionDto.getTotal());


        JasperReport jasperReport= JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        OutputStream outputStream=new FileOutputStream(new File(outputfile));
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//        JasperReport jasperReport= JasperCompileManager.compileReport(design);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//        JasperViewer.viewReport(jasperPrint,false);


    }

    private void generateVaccine(Long petId) throws JRException, FileNotFoundException {
        System.out.println("came");
        List<PetDetailDto> petDetailDtos=petDetailService.getAll();
        List <VaccineMappingDto> list =new ArrayList<>();
        for (PetDetailDto dto:petDetailDtos) {
            if(dto.getPetId().equals(petId)){
                VaccineMappingDto mapObj= new VaccineMappingDto();
                mapObj.setVaccineId(dto.getVaccineId());
                mapObj.setDosage(dto.getDosage());
                mapObj.setGiven(dto.getGiven()?"Given":"Not Given");
                mapObj.setDate(dto.getDate());
                mapObj.setName(vaccineService.getById(dto.getVaccineId()).getVaccineName());
                list.add(mapObj);
                System.out.println(mapObj);
            }
        }


        JRBeanCollectionDataSource itemsJBean= new JRBeanCollectionDataSource(list);

        String outputfile = "src/main/resources/reports/PdfFiles/"+petId+"EbookVac.pdf";
        JasperDesign design= JRXmlLoader.load("src/main/resources/reports/VaccineReport.jrxml");
        Map<String, Object> parameters = new HashMap<>();


        parameters.put("VaccineDetailSet",itemsJBean);



        JasperReport jasperReport= JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        OutputStream outputStream=new FileOutputStream(new File(outputfile));
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
    private  void mergePDFs(List<String> inputFiles, String outputFile) {
        PDFMergerUtility merger = new PDFMergerUtility();

        for (String inputFile : inputFiles) {
            try {
                merger.addSource(new File(inputFile));
            } catch (IOException e) {
                System.err.println("Error adding file " + inputFile + ": " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        merger.setDestinationFileName(outputFile);

        try {
            merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (IOException e) {
            System.err.println("Error merging PDFs: " + e.getMessage());
        }

    }
    private void deleteFile(String filePath){


        // Create a File object
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {
            // Attempt to delete the file
            boolean isDeleted = file.delete();

            if (isDeleted) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
