package com.perficient.domain;

import com.perficient.Exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
  Course course;
  @BeforeEach
  public void setUp() {
    course = new Course();
  }
  @Test
  void getCourseId() {
    Long idValue = 4L;

    this.course.setCourseId(idValue);

    assertEquals(idValue, course.getCourseId());
  }
  @Test
  void getName() {
    String name = "Test";

    course.setName(name);

    assertEquals(name, course.getName());
  }

  @Test
  void getCredits() {
    Integer credits = 4;

    course.setCredits(credits);

    assertEquals(credits, course.getCredits());
  }
}