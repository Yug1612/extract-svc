package com.pravisht.extract.listener;

import com.pravisht.extract.dao.entities.ExtractRequestEntity;
import com.pravisht.extract.dao.repo.ExtractRequestRepository;
import com.pravisht.extract.enums.ExtractRequestStatus;
import com.pravisht.extract.factory.CSVGeneratorFactory;
import com.pravisht.extract.service.CSVGeneratorService;
import com.pravisht.extract.service.impl.S3FileUploadService;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ExtractRequestListener {

    @Autowired
    private ExtractRequestRepository extractRequestRepository;

    @Autowired
    private CSVGeneratorFactory csvGeneratorFactory;

    @Autowired
    @Lazy
    private S3FileUploadService fileUploadService;


    public void processExtractRequest(String id) {
        ExtractRequestEntity extractRequest = extractRequestRepository.findById(id).get();
        extractRequest.setStatus(ExtractRequestStatus.InProgress);
        extractRequestRepository.save(extractRequest);
        log.info("Processing processExtractRequest tenant {} requestId {} status {} ",extractRequest.getTenantId(),extractRequest.getId(),extractRequest.getStatus());
        try {
            CSVGeneratorService csvGeneratorService = csvGeneratorFactory.getCSVGenerator(extractRequest.getExtractDocument());

            File file = csvGeneratorService.csvGenerator(extractRequest);
            //encrypt file using pgp encryption
            String tenantId = "max";
            if(file!=null) {
                fileUploadService.uploadFile(tenantId, file, extractRequest.getExtractDocument().name());
                extractRequest.setStatus(ExtractRequestStatus.UploadLocalCloudStorage);
            }else {
                extractRequest.setStatus(ExtractRequestStatus.Success_WITH_NO_UPLOAD);
            }

            log.info("{} CSV file uploaded tenant {} requestId {} status {} ",extractRequest.getExtractDocument(),extractRequest.getTenantId(),extractRequest.getId(),extractRequest.getStatus());

        } catch (Exception e) {
            extractRequest.setStatus(ExtractRequestStatus.Failure);
            extractRequest.setErrorMsg(e.getMessage());
            log.error("Exception while processing processExtractRequest tenant {} requestId {} status {} ",extractRequest.getTenantId(),extractRequest.getId(),extractRequest.getStatus());
        }
        extractRequestRepository.save(extractRequest);
    }
}
