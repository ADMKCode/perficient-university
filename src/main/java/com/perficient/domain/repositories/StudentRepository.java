package com.perficient.domain.repositories;

import com.perficient.domain.Course;
import com.perficient.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
  Page<Student> findAll(Pageable pageable);
}
