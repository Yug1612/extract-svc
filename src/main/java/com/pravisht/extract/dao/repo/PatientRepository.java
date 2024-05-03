package com.pravisht.extract.dao.repo;

import com.pravisht.extract.dao.entities.Patient;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    @Query("{'patientGuid':?0}")
    List<Patient> findByPatientGuid(String patientGuid);

    @Query("{'createdDate':{$gt:?0,$lte:?1}}")
    List<Patient> findAllByDateRange(LocalDate startDate,LocalDate endDate);

}