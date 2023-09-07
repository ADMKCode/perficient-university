package com.perficient.component;

import com.perficient.domain.Course;
import com.perficient.domain.repositories.CourseRepository;
import com.perficient.domain.repositories.StudentRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseInit implements ApplicationListener<ContextRefreshedEvent> {
  private final CourseRepository courseRepository;
  private final StudentRepository studentRepository;

  public CourseInit(CourseRepository courseRepository, StudentRepository studentRepository) {
    this.courseRepository = courseRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    List<Course> courses = new ArrayList<>(2);
  }
}
