package com.pravisht.extract.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ExtractRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String query;
}
