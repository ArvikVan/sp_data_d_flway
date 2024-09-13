package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDto {
    private String regNumber;
    private String regDate;
    private String outNumber;
    private String outDate;
    private String deliveryMethod;
    private String correspondent;
    private String subject;
    private String description;
    private String executionDate;
    private String access;
    private String control;
    private MultipartFile fileUpload;
}
