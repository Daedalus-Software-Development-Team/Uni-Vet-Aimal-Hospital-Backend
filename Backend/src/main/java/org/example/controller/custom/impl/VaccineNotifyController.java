package org.example.controller.custom.impl;

import org.example.dto.CustomerDto;
import org.example.dto.EmailDto;
import org.example.dto.PetDetailDto;
import org.example.dto.PetDto;
import org.example.service.custom.CustomerService;
import org.example.service.custom.PetDetailService;
import org.example.service.custom.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class VaccineNotifyController {

    @Autowired
    PetService petService;

    @Autowired
    PetDetailService petDetailService;

    @Autowired
    CustomerService customerService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Scheduled(cron = "0 0 17 * * *")  //5:00 PM every day.
    public void execute() throws Exception {
        System.out.println("Code is being executed... Time: " + formatter.format(LocalDateTime.now()));

        List<PetDetailDto> petDetailList = petDetailService.getAll();

        for (PetDetailDto petDetailDto:petDetailList) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(petDetailDto.getDate());

            String string = null;
            try {
                // This will print 20100315
                string = previousDateString(date.toString());
            } catch (ParseException e) {
                System.out.println("Invalid date string");
                e.printStackTrace();
            }

            if (string.equals(LocalDate.now())) {
                PetDto petDto = petService.getById(petDetailDto.getPetId());
                CustomerDto customer = customerService.getById(petDto.getCustomerId());
                String email = customer.getEmail();
                EmailDto e= new EmailDto(email,"Uni-Vet Vaccination Alert","Your pets vaccination is due tomorrow",null);
                e.sendEmail();
            }
        }
    }
    public static String previousDateString(String dateString)
            throws ParseException {
        // Create a date formatter using your format string
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        // Parse the given date string into a Date object.
        // Note: This can throw a ParseException.
        Date myDate = dateFormat.parse(dateString);

        // Use the Calendar class to subtract one day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        // Use the date formatter to produce a formatted date string
        Date previousDate = calendar.getTime();
        String result = dateFormat.format(previousDate);

        return result;
    }
}

