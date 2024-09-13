package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regNumber;
    private LocalDate regDate;
    private String outNumber;
    private LocalDate outDate;
    private String deliveryMethod;
    private String correspondent;
    private String subject;
    private String description;
    private LocalDate executionDate;
    private String access;
    private String control;

    // Конструкторы, геттеры и сеттеры

    public Document() {}

    public Document(String regNumber, LocalDate regDate, String outNumber, LocalDate outDate,
                    String deliveryMethod, String correspondent, String subject, String description,
                    LocalDate executionDate, String access, String control) {
        this.regNumber = regNumber;
        this.regDate = regDate;
        this.outNumber = outNumber;
        this.outDate = outDate;
        this.deliveryMethod = deliveryMethod;
        this.correspondent = correspondent;
        this.subject = subject;
        this.description = description;
        this.executionDate = executionDate;
        this.access = access;
        this.control = control;
    }

    // геттеры и сеттеры
}

