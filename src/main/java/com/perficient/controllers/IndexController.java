package com.perficient.controllers;


import com.perficient.domain.Course;
import com.perficient.dto.CourseDTO;
import com.perficient.services.springdatajpa.CourseSDJpaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
  private final CourseSDJpaService courseService;

  public IndexController(CourseSDJpaService courseService) {
    this.courseService = courseService;
  }

  @RequestMapping(value = "/courses", method = RequestMethod.GET)
  public ResponseEntity<Page<CourseDTO>> getAllCourses(int pageNumber, int pageSize){
    Page<CourseDTO> paginatedCourses = courseService.getPaginatedCourses(pageNumber, pageSize);
    return ResponseEntity.ok(paginatedCourses);
  }
  @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
  public CourseDTO findCourse(@PathVariable("id") Long id){
    return courseService.findByIDDTO(id);
  }

  @RequestMapping(value = "/courses/", method = RequestMethod.POST)
  public ResponseEntity createCourse(@Valid @RequestBody  CourseDTO courseInput){
    var auth = SecurityContextHolder.getContext().getAuthentication();
    Course course = courseService.convertToCourse(courseInput);
    courseService.save(course);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/courses/", method = RequestMethod.PUT)
  public ResponseEntity updateCourse(@Valid@RequestBody CourseDTO courseInput){
    Course course = courseService.convertToCourse(courseInput);
    courseService.save(course);
    return new ResponseEntity(HttpStatus.ACCEPTED);
  }
  @RequestMapping(value = "/courses/{courseId}", method = RequestMethod.DELETE)
  public void deleteCourseById(@PathVariable("courseId") Long id){
    courseService.deleteByID(id);
  }
  
}
