package org.example.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Test {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Scheduled(cron = "0 0 17 * * *")  //5:00 PM every day.
    public void execute() throws InterruptedException {
        System.out.println("Code is being executed... Time: " + formatter.format(LocalDateTime.now()));

    }
}
