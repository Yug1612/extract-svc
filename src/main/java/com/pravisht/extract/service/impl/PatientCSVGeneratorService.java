package com.pravisht.extract.service.impl;

import com.opencsv.CSVWriter;
import com.pravisht.extract.dao.entities.ExtractRequestEntity;
import com.pravisht.extract.dao.entities.Patient;
import com.pravisht.extract.dao.repo.PatientRepository;
import com.pravisht.extract.enums.SchemaType;
import com.pravisht.extract.service.CSVGeneratorService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PatientCSVGeneratorService implements CSVGeneratorService {

    @Autowired
    private PatientRepository patientRepository;
    @Override
    public File csvGenerator(ExtractRequestEntity extractRequest)  {
        File file = null;
        LocalDate startDate = extractRequest.getStartDate();
        LocalDate endDate = extractRequest.getEndDate();
        List<Patient> patients= patientRepository.findAllByDateRange(startDate,endDate);
        String userDir = System.getProperty("user.home");
        String filePath = userDir + File.separator + "patient_" + currentTimeStamp() +"_"+extractRequest.getId()+ ".csv";
        log.info("Generating CSV file of PatientEntity at {}", filePath);

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] header = SchemaType.PATIENT.getDefaultFieldNames();
            writer.writeNext(header);

            patients.stream().forEach(patient -> {
                        String dataRow[] = new String[]{patient.getNhsNumber(), patient.getGivenName(),
                                patient.getSex(),
                                patient.getDateOfBirth().toString(),
                                patient.getCounty(),
                                patient.getMobilePhone(),
                                };

                        writer.writeNext(dataRow);
                    }
            );
            file = new File(filePath);
        } catch (IOException ex) {
            log.error("Error writing data to CSV file {}", ex);
        }
        log.info("Done with Generating CSV file of PatientEntity at {}", filePath);
        return file;
    }
    private String currentTimeStamp() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the format for the timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        // Format the current date and time according to the specified format
        return currentDateTime.format(formatter);
    }
}
