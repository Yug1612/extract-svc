package com.pravisht.extract.dao.entities;

import com.pravisht.extract.enums.ExtractDocument;
import com.pravisht.extract.enums.ExtractRequestStatus;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("extract_request")
public class ExtractRequestEntity extends BaseEntity {
    @Id
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private ExtractDocument extractDocument;
    private String query;
    private ExtractRequestStatus status;
    private String extractFile;
    private String errorMsg;
}
