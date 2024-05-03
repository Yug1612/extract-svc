package com.pravisht.extract.dao.entities;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class BaseEntity {
    @Field(value = "created_by")
    @CreatedBy
    private String createdBy;

    @Field(value = "updated_by")
    @LastModifiedBy
    private String updatedBy;

    @Field(value = "created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Field(value = "updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Field(value = "version")
    private String version;

    @Field
    private String tenantId;

}
