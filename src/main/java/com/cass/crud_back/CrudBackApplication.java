package com.cass.crud_back;

import com.cass.crud_back.enums.Category;
import com.cass.crud_back.model.Course;
import com.cass.crud_back.model.Lesson;
import com.cass.crud_back.repo.CourseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Locale;

@SpringBootApplication
public class CrudBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudBackApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner initDataBase(CourseRepo courseRepo) {
        return args -> {
            courseRepo.deleteAll();

            Course c = new Course();
            c.setName("Spring + Angular");
            c.setCategory(Category.BACK_END);

            Lesson l = new Lesson();
            l.setName("Intro");
            l.setYoutubeUrl("watch:?=www");
            l.setCourse(c);
            c.getLessons().add(l);

            courseRepo.save(c);
        };
    }
}
