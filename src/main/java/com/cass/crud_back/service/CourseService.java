package com.cass.crud_back.service;

import com.cass.crud_back.model.Course;
import com.cass.crud_back.repo.CourseRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {

    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> list() {
        return courseRepo.findAll();
    }

    public Optional<Course> findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepo.findById(id);
    }

    public Course create(@Valid Course course) {
        return courseRepo.save(course);
    }

    public Optional<Course> update(@NotNull @Positive Long id,
                                   @Valid Course course) {
        return courseRepo.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepo.save(recordFound);
                });
    }

    public boolean delete(@NotNull @Positive Long id) {
        return courseRepo.findById(id)
                .map(recordFound -> {
                    courseRepo.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
