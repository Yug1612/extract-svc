package com.pravisht.extract.dao.entities;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("patient")
public class Patient extends BaseEntity {
    @Id
    private String patientGuid;
    private String organisationGuid;
    private String usualGpUserInRoleGuid;
    private String sex;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String title;
    private String givenName;
    private String middleNames;
    private String surname;
    private LocalDate dateOfRegistration;
    private String nhsNumber;
    private int patientNumber;
    private String patientTypeDescription;
    private boolean dummyType;
    private String houseNameFlatNumber;
    private String numberAndStreet;
    private String village;
    private String town;
    private String county;
    private String postcode;
    private String residentialInstituteCode;
    private String nHSNumberStatus;
    private String carerName;
    private String carerRelation;
    private String personGuid;
    private LocalDate dateofDeactivation;
    private boolean deleted;
    private boolean spineSensitive;
    private boolean isConfidential;
    private String emailAddress;
    private String homePhone;
    private String mobilePhone;
    private String externalUsualGPGuid;
    private String externalUsualGP;
    private String externalUsualGPOrganisation;
    private int processingId;
}
