package com.pravisht.extract.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AWSConfig {
    @Value("${spring.cloud.aws.region.static:default}")
    private String region;
    @Value("${spring.cloud.aws.credentials.access-key:default}")
    private String accessKey;
    @Value("${spring.cloud.aws.credentials.secret-key:default}")
    String secretKey;

    private static final String DEFAULT = "default";

    @Bean
    @Lazy
    AmazonS3 amazonS3Client() {
        // Configure and create an Amazon S3 client instance
        AmazonS3 amazonS3 = null;
        if (DEFAULT.equals(region) || DEFAULT.equals(accessKey) || DEFAULT.equals(secretKey)) {
            amazonS3 =
                    AmazonS3Client.builder()
                            .withRegion(region)
                            .withCredentials(new DefaultAWSCredentialsProviderChain())
                            .build();
        } else {
            amazonS3 =
                    AmazonS3Client.builder()
                            .withRegion(region)
                            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                            .build();
        }
        return amazonS3;
    }
}
