package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.MAY;
import static java.util.Calendar.SEPTEMBER;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student emmanuel = new Student(
                    "Emmanuel",
                    "emmyolusola.eo@gmail.com",
                    LocalDate.of(1995, MAY, 5)
            );
            Student annabel = new Student(
                    "Annabel",
                    "anna-bel@gmail.com",
                    LocalDate.of(1990, SEPTEMBER, 5)
            );

            repository.saveAll(List.of(emmanuel, annabel));
        };

    }
}
