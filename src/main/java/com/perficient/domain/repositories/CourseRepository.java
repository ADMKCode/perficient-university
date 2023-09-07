package com.perficient.domain.repositories;

import com.perficient.domain.Course;
import com.perficient.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Optional<Course> findByDescription(String description);
  Page<Course> findAll(Pageable pageable);
}
