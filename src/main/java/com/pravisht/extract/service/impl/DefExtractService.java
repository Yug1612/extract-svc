package com.pravisht.extract.service.impl;

import com.pravisht.extract.dao.entities.ExtractRequestEntity;
import com.pravisht.extract.dao.repo.ExtractRequestRepository;
import com.pravisht.extract.dto.request.ExtractRequest;
import com.pravisht.extract.enums.ExtractDocument;
import com.pravisht.extract.enums.ExtractRequestStatus;
import com.pravisht.extract.listener.ExtractRequestListener;
import com.pravisht.extract.service.ExtractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefExtractService implements ExtractService {

    @Autowired
    private ExtractRequestRepository extractRequestRepository;

    @Autowired
    private ExtractRequestListener extractRequestListener;
    @Override
    public String extract(ExtractDocument extractDocument, ExtractRequest patientExtractRequest) {
        ExtractRequestEntity extractRequest = new ExtractRequestEntity();
        extractRequest.setStartDate(patientExtractRequest.getStartDate());
        extractRequest.setEndDate(patientExtractRequest.getEndDate());
        extractRequest.setStatus(ExtractRequestStatus.InProgress);
        extractRequest.setExtractDocument(extractDocument);
        ExtractRequestEntity save = extractRequestRepository.save(extractRequest);
        log.info("Processing on demand extract for tenant {} document {} requestId {}","max",extractDocument,save.getId());
        extractRequestListener.processExtractRequest(save.getId());
        return save.getId();
    }
}
