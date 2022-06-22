package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Bean
    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentWithId(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Student with id" + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String studentName, String studentEmail) {
//        Optional<Student> optionalStudent = studentRepository.findById(studentId);
//        if (optionalStudent.isEmpty()){
//            throw new IllegalStateException("Student with id " + studentId + "does not exist");
//        }
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student with id " + studentId + "does not exist"));

        System.out.println(studentId + "\n" + studentName + "\n" + studentEmail);
        student.setName(studentName);
        student.setEmail(studentEmail);
//        Student student = studentRepository.getReferenceById(studentId);
//        if (studentName != null && studentName.length() > 0 && !Objects.equals(student.getName(), studentName)){
//            student.setName(studentName);
//        }
//        if (studentEmail !=null && studentEmail.length() > 0 && !Objects.equals(student.getEmail(), studentEmail)){
//            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
//            if (studentOptional.isPresent()){
//                throw new IllegalStateException("Email already taken");
//            }
//            student.setEmail(studentEmail);
//        }
//        studentRepository.save(student);
//        studentRepository.saveAndFlush(student);
    }

    public void deleteAllStudent() {
        studentRepository.deleteAll();
    }
}
