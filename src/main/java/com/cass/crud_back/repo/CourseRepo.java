package com.cass.crud_back.repo;

import com.cass.crud_back.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CourseRepo extends JpaRepository<Course, Long> {

}
