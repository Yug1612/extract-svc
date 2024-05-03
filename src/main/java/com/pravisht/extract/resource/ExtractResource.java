package com.pravisht.extract.resource;


import com.pravisht.extract.dto.request.ExtractRequest;
import com.pravisht.extract.enums.ExtractDocument;
import com.pravisht.extract.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/extract")
public class ExtractResource {
    @Autowired
    private ExtractService extractService;
    @PostMapping("/{extractDocument}")
    public ResponseEntity<ApiSuccessRes<String>> patientExtract(@PathVariable("extractDocument") ExtractDocument extractDocument, @RequestBody ExtractRequest extractRequest) {
        String response = extractService.extract(extractDocument,extractRequest);
        return new ResponseEntity<>(ApiSuccessRes.<String>builder().data(response).build(), HttpStatus.OK);
    }
}
