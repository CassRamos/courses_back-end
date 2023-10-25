package com.cass.crud_back.service;

import com.cass.crud_back.dto.CourseDTO;
import com.cass.crud_back.dto.mapper.CourseMapper;
import com.cass.crud_back.exception.RecordNotFoundException;
import com.cass.crud_back.repo.CourseRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepo courseRepo, CourseMapper courseMapper) {
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return courseRepo.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepo
                .findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepo.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id,
                            @Valid @NotNull CourseDTO course) {
        return courseRepo.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(course.category()));
                    return courseMapper.toDTO(courseRepo.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepo.delete(courseRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
