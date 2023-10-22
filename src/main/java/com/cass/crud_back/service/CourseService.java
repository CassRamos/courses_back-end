package com.cass.crud_back.service;

import com.cass.crud_back.exception.RecordNotFoundException;
import com.cass.crud_back.model.Course;
import com.cass.crud_back.repo.CourseRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    public Course findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepo
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@Valid Course course) {
        return courseRepo.save(course);
    }

    public Course update(@NotNull @Positive Long id,
                         @Valid Course course) {
        return courseRepo.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepo.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepo.delete(courseRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
