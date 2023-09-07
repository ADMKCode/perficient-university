package com.perficient.services.springdatajpa;

import com.perficient.Exceptions.NotFoundException;
import com.perficient.domain.Course;
import com.perficient.domain.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CourseSDJpaServiceTest {
  CourseSDJpaService courseSDJpaService;

  @Mock
  CourseRepository courseRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    courseSDJpaService = new CourseSDJpaService(courseRepository);
  }

  @Test
  void findAll() {
    Course course = new Course();
    List<Course> coursesData = new ArrayList<>();
    coursesData.add(course);

    when(courseRepository.findAll()).thenReturn(coursesData);

    List<Course> recipes = courseRepository.findAll();

    assertEquals(recipes.size(), 1);
  }

  @Test
  void getAllCourses() {
  }

  @Test
  void getPaginatedCourses() {
  }

  @Test
  void convertToDTO() {
  }

  @Test
  void validate() {
  }

  @Test
  void findByID() {
    Course course = new Course();
    course.setCourseId(1L);
    Optional<Course> courseOptional = Optional.of(course);

    when(courseRepository.findById(anyLong())).thenReturn(courseOptional);

    Optional<Course> courseReturned = courseRepository.findById(1L);

    assertNotNull(courseReturned.get());
    verify(courseRepository, times(1)).findById(anyLong());
    verify(courseRepository, never()).findAll();
  }

  @Test
  void findByIDNotFound() {
    assertThrows(NotFoundException.class, () -> {
      courseSDJpaService.findByID(1L);
    });
  }

  @Test
  void findByIDDTO() {
  }

  @Test
  void save() {
    Course course = new Course();
    course.setCourseId(9L);
    course.setName("Test");
    course.setCredits(2);
    course.setPrice(11);
    course.setArea("TEST");
    course.setDescription("Description");

    when(courseRepository.save(any())).thenReturn(course);
    Course courseReturned = courseRepository.save(course);

    assertEquals(course.getCourseId(), courseReturned.getCourseId());
  }

  @Test
  void deleteByID() {
    Long idToDelete = Long.valueOf(2L);
    Course course = new Course();
    course.setCourseId(idToDelete);

    //when
    courseRepository.deleteById(course.getCourseId());

    //no 'when', since method has void return type

    //then
    verify(courseRepository, times(1)).deleteById(anyLong());
  }
}