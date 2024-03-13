package org.example.dto;

public class VaccineMappingDto {
    Long vaccineId;
    String name;
    String given;
    String date;
    String dosage;

    public VaccineMappingDto() {
    }



    public VaccineMappingDto(Long vaccineId, String name, String given, String date, String dosage) {
        this.vaccineId = vaccineId;
        this.name = name;
        this.given = given;
        this.date = date;
        this.dosage = dosage;
    }



    public Long getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }


}
