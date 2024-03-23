package com.example.StudentManagementApplication.service;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class StudentValidation {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Marks are required")
    private Double marks;

    @NotEmpty(message = "Address is required")
    private String address;


}