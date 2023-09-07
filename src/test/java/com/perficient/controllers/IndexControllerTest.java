package com.perficient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.perficient.domain.Area;
import com.perficient.domain.Course;
import com.perficient.domain.repositories.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class IndexControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CourseRepository courseRepository;

  //@Test
  void getCourses() throws Exception {
    Course course = getCourse();
    List<Course> courses = Arrays.asList(course);
    when(courseRepository.findAll()).thenReturn(courses);
    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    mockMvc.perform(get("/courseapi").contextPath("/courseapi/courses"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectWriter.writeValueAsString(courses)));
  }

  private static Course getCourse() {
    Course course = new Course();
    course.setName("Test");
    course.setDescription("Description");
    return course;
  }

}