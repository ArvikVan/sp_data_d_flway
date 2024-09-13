package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
public class DocumentController {

    private final DocumentRepository documentRepository;
    @GetMapping("/document-form")
    public String showDocumentForm() {
        return "document-form"; // имя файла в папке templates без расширения .html
    }

    @PostMapping
    public ResponseEntity<String> saveDocument(
            @RequestParam("jsonData") String jsonData,
            @RequestParam("fileUpload") MultipartFile fileUpload) {

        // Преобразование JSON строки в объект
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentDto documentDto = null;
        log.info(jsonData);
        try {
            documentDto = objectMapper.readValue(jsonData, DocumentDto.class);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Ошибка обработки JSON данных.");
        }
// Преобразование строк в LocalDate
        LocalDate regDate, outDate = null, executionDate = null;
        try {
            regDate = LocalDate.parse(documentDto.getRegDate());
            if (documentDto.getOutDate() != null && !documentDto.getOutDate().isEmpty()) {
                outDate = LocalDate.parse(documentDto.getOutDate());
            }
            if (documentDto.getExecutionDate() != null && !documentDto.getExecutionDate().isEmpty()) {
                executionDate = LocalDate.parse(documentDto.getExecutionDate());
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Некорректный формат даты.");
        }
        // Проверка на обязательные поля
        if (documentDto.getRegNumber() == null || documentDto.getRegNumber().isEmpty() ||
                documentDto.getRegDate() == null || documentDto.getSubject() == null ||
                documentDto.getSubject().isEmpty()) {
            return ResponseEntity.badRequest().body("Заполните выделенные поля!");
        }

        // Проверка размера и типа файла
        if (!fileUpload.isEmpty()) {
            if (fileUpload.getSize() > 1048576) {
                return ResponseEntity.badRequest().body("Размер файла превышает 1Мб.");
            }

            String contentType = fileUpload.getContentType();
            if (!("application/pdf".equals(contentType) || "application/msword".equals(contentType) ||
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType))) {
                return ResponseEntity.badRequest().body("Недопустимый формат файла.");
            }
        }

        // Сохранение данных в базу
        Document document = new Document(documentDto.getRegNumber(), regDate, documentDto.getOutNumber(),
                outDate, documentDto.getDeliveryMethod(), documentDto.getCorrespondent(),
                documentDto.getSubject(), documentDto.getDescription(), executionDate,
                documentDto.getAccess(), documentDto.getControl());

        documentRepository.save(document);

        return ResponseEntity.ok("Документ сохранен успешно!");
    }
}
