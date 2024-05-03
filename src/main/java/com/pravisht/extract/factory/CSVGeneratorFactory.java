package com.pravisht.extract.factory;

import com.pravisht.extract.enums.ExtractDocument;
import com.pravisht.extract.service.CSVGeneratorService;
import com.pravisht.extract.service.impl.PatientCSVGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CSVGeneratorFactory {
    @Autowired
    private ApplicationContext applicationContext;
public CSVGeneratorService getCSVGenerator(ExtractDocument extractDocument) {
    switch (extractDocument) {
        case Patient -> {
            return applicationContext.getBean(PatientCSVGeneratorService.class);
        }
        default -> {
            return null;
        }
    }

}
}
