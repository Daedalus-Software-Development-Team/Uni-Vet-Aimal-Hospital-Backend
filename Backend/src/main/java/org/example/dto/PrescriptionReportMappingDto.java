package org.example.dto;

public class PrescriptionReportMappingDto {
    public PrescriptionReportMappingDto() {
    }
    Long medicineId;
    String medicineName;
    String dosage;
    String timing;
    String dailyQuantity;
    String days;
    String available;
    String price;

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDailyQuantity() {
        return dailyQuantity;
    }

    public void setDailyQuantity(String dailyQuantity) {
        this.dailyQuantity = dailyQuantity;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public PrescriptionReportMappingDto(Long medicineId, String medicineName, String dosage, String timing, String dailyQuantity, String days, String available, String price) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.timing = timing;
        this.dailyQuantity = dailyQuantity;
        this.days = days;
        this.available = available;
        this.price = price;
    }




}
