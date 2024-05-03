package com.pravisht.extract.service;

import com.pravisht.extract.dto.request.ExtractRequest;
import com.pravisht.extract.enums.ExtractDocument;

public interface ExtractService {

     String extract(ExtractDocument extractDocument, ExtractRequest extractRequest);
}
