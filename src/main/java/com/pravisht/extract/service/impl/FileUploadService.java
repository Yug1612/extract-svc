package com.pravisht.extract.service.impl;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileUploadService {
public void uploadFile(String tenantId, File file, String fileType) throws FileNotFoundException;
}
