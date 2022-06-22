package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "management/api/v1/students")
public class AdminController {

    private StudentService studentService;

    @Autowired
    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudentWithId() {
        return studentService.getStudents();
    }

    @GetMapping(path = "/{studentId}")
    public Optional<Student> getStudentWithId(@PathVariable("studentId") Long studentId) {
        return studentService.getStudentWithId(studentId);
    }

    @PostMapping(path = "/post")
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @PutMapping(path = "/update/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
        System.out.println(studentId + "\n" + name + "\n" + email);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @DeleteMapping(path = "/delete_all")
    public void deleteAllStudents() {
        studentService.deleteAllStudent();
    }
}
