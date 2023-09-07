package com.perficient.domain;

import com.perficient.Exceptions.NotFoundException;
import com.perficient.domain.repositories.CourseRepository;
import com.perficient.services.springdatajpa.CourseSDJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseTest {

  CourseSDJpaService courseSDJpaService;

  @Mock
  CourseRepository courseRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    courseSDJpaService = new CourseSDJpaService(courseRepository);
  }

  @Test
  public void getCourseByIdTestNotFound(){
    assertThrows(NotFoundException.class, () -> {
      courseSDJpaService.findByID(1L);
    });
  }

}