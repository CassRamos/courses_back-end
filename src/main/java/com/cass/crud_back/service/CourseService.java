package com.cass.crud_back.service;

import com.cass.crud_back.dto.CourseDTO;
import com.cass.crud_back.dto.CoursePageDTO;
import com.cass.crud_back.dto.mapper.CourseMapper;
import com.cass.crud_back.exception.RecordNotFoundException;
import com.cass.crud_back.model.Course;
import com.cass.crud_back.model.Lesson;
import com.cass.crud_back.repo.CourseRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public CoursePageDTO list(@PositiveOrZero int page,
                              @Positive @Max(100) int pageSize) {
        Page<Course> coursePage = courseRepo
                .findAll(PageRequest.of(page, pageSize));
        List<CourseDTO> coursesDTO = coursePage
                .get()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
        return new CoursePageDTO(coursesDTO, coursePage.getTotalElements(), coursePage.getTotalPages());
    }

   /* public List<CourseDTO> list() {
        return courseRepo.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }
*/

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
                            @Valid @NotNull CourseDTO courseDTO) {
        return courseRepo.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));

                    recordFound.getLessons().clear();

                    for (Lesson lesson : course.getLessons()) {
                        lesson.setCourse(recordFound); // Set the course reference for the lesson to the updated course
                        recordFound.getLessons().add(lesson); // Set the course reference for the lesson to the updated course
                    }

                    return courseMapper.toDTO(courseRepo.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepo.delete(courseRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
