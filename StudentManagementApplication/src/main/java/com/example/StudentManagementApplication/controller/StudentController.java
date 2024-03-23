package com.example.StudentManagementApplication.controller;

import com.example.StudentManagementApplication.entity.Student;
import com.example.StudentManagementApplication.service.StudentService;
import com.example.StudentManagementApplication.service.StudentValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/add-student")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/addStudent")
    public String addStudent(@Validated @RequestBody StudentValidation studentValidation, BindingResult result) {
        if (result.hasErrors()) {
            return "Validation failed: " + result.getAllErrors().toString();
        }

        Student student = convertToStudent(studentValidation);
        studentService.addStudent(student);

        logger.info("Student added: {}", student);
        return "redirect:/view-students";
    }


    @GetMapping("/view-students")
    public String showAllStudents(Model model) {
        Iterable<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "view-students";
    }

    // Helper method to convert StudentValidation to Student
    private Student convertToStudent(StudentValidation studentValidation) {
        Student student = new Student();
        BeanUtils.copyProperties(studentValidation, student);
        return student;
    }
}
