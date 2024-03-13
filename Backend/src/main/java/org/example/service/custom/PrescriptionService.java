package org.example.service.custom;

import net.sf.jasperreports.engine.JRException;
import org.example.dto.PrescriptionDto;
import org.example.entity.PrescriptionEntity;
import org.example.service.CrudService;

import java.io.FileNotFoundException;

public interface PrescriptionService extends CrudService<PrescriptionEntity, PrescriptionDto> {
    void generateReport(Long prescriptionId) throws JRException, FileNotFoundException;
}
