package com.pravisht.extract.enums;

public enum ExtractRequestStatus {
    Pending,//When request initiated
    InProgress,//When request pick by listener
    UploadLocalCloudStorage,//When file uploaded to s3 cloud storage
    Failure,
    Success,//When file is uploaded to sftp location
    Success_WITH_NO_UPLOAD//
    ;
    }
