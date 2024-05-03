package com.pravisht.extract.enums;

public enum SchemaType {
    PATIENT(new String[]{"NHSNumber", "Name", "RecordVersion", "Gender", "BirthDate", "PlaceOfBirth", "Address", "Contacts", "PreferredContactDetails", "GeneralPractice", "PatientCommunicationsLanguage"}), ORGANISATION(new String[]{""}),
    PATIENT_v2(new String[]{"NHSNumber", "Name", "Gender", "BirthDate", "PlaceOfBirth", "Address", "Contacts", "PreferredContactDetails", "GeneralPractice", "PatientCommunicationsLanguage","ORGANISATION"})
    ;

    String[] defaultFieldNames;

    SchemaType(String[] fieldsName) {
        this.defaultFieldNames = fieldsName;
    }

    public String[] getDefaultFieldNames() {
        return this.defaultFieldNames;
    }
}
