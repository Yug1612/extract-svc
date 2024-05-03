package com.pravisht.extract.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

@Service
public class S3FileUploadService implements FileUploadService{
    @Value("${im1.extract.location}")
    private String baseLocation;
    String RESOURCE_PATH_SEPARATOR ="/";
    Integer TRANSFER_BUFFER_SIZE = 4096;

    private static final Logger logger = LoggerFactory.getLogger(S3FileUploadService.class);


    @Autowired
    @Lazy
    private AmazonS3 amazonS3;

    @Autowired
    private ResourceLoader resourceLoader;
//    @PostConstruct()
    public void init() {
        if (!amazonS3.doesBucketExistV2(baseLocation)) {
            amazonS3.createBucket(baseLocation);
        }
    }

    @Override
    public void uploadFile(String tenantId, File file,String fileType) throws FileNotFoundException {
        String contentLocationPath = "s3://" + baseLocation
                + RESOURCE_PATH_SEPARATOR + tenantId;
        String contentResourceIdentifier = file.getName();
        WritableResource resource = (WritableResource) resourceLoader.getResource(contentLocationPath+RESOURCE_PATH_SEPARATOR+contentResourceIdentifier);
        InputStream inputStream = new FileInputStream(file);
        try (OutputStream outputStream = resource.getOutputStream()) {
            copyOutputStreamToInputStream(inputStream, outputStream);
        } catch (Exception ex) {
            // if anything goes wrong, tidy up and throw an exception
            cleanupResourceIfExists(contentLocationPath, contentResourceIdentifier);

            throw new RuntimeException("Error writing content to store resource: " + contentLocationPath
                    + RESOURCE_PATH_SEPARATOR + contentResourceIdentifier, ex);

        }
    }

    private void copyOutputStreamToInputStream(InputStream input, OutputStream output) throws IOException {

        byte[] buffer = new byte[TRANSFER_BUFFER_SIZE];

        int bytesRead;

        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    private void cleanupResourceIfExists(String resourcePath, String resourceIdentifier) {
        try {
            amazonS3.deleteObject(resourcePath, resourceIdentifier);
        } catch (Exception ex) {

            // nothing we can do here, if it does go wrong, so just log and move on
            logger.error("Error while attempting to remove a resource following a write failure for resource: {}/{}",
                    resourcePath, resourceIdentifier, ex);
        }
    }

}
