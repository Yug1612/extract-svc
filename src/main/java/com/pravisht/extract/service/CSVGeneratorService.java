package com.pravisht.extract.service;

import com.pravisht.extract.dao.entities.ExtractRequestEntity;
import java.io.File;
import java.io.IOException;

public interface CSVGeneratorService {
    File csvGenerator(ExtractRequestEntity extractRequest) throws IOException;
}
