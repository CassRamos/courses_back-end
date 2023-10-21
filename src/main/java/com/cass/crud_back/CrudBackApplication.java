package com.cass.crud_back;

import com.cass.crud_back.model.Course;
import com.cass.crud_back.repo.CourseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudBackApplication.class, args);
    }

    @Bean
    CommandLineRunner initDataBase(CourseRepo courseRepo) {
        return args -> {
            courseRepo.deleteAll();

            Course c = new Course();
            c.setName("Spring + Angular");
            c.setCategory("Back-end");

            courseRepo.save(c);
        };
    }
}
