package com.cass.crud_back.controller;

import com.cass.crud_back.model.Course;
import com.cass.crud_back.repo.CourseRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated //activates validations in javabean package (in this case @NotNull & @Positive)
@RestController
@RequestMapping("/api/courses")

public class CourseController {

    private final CourseRepo courseRepo;

    public CourseController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseRepo.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepo.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    //@ResponseStatus(code = HttpStatus.CREATED it's the same as ResponseEntity.status(HttpStatus.CREATED)
    public ResponseEntity<Course> create(@RequestBody @Valid Course course) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseRepo.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id,
                                         @RequestBody @Valid Course course) {
        return courseRepo.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    Course updated = courseRepo.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        return courseRepo.findById(id)
                .map(recordFound -> {
                    courseRepo.deleteById(id);
                    return ResponseEntity.noContent().<Void>build(); //Void return type can be replaced by <?> or <Object>, all these values can be a return for delete methods
                }).orElse(ResponseEntity.notFound().build());
    }
}
