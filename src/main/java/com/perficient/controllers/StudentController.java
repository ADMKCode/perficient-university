package com.perficient.controllers;


import com.perficient.domain.Course;
import com.perficient.domain.Student;
import com.perficient.dto.CourseDTO;
import com.perficient.dto.StudentDTO;
import com.perficient.services.springdatajpa.CourseSDJpaService;
import com.perficient.services.springdatajpa.StudentSDJpaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
  private final StudentSDJpaService studentService;

  public StudentController(StudentSDJpaService studentService) {
    this.studentService = studentService;
  }

  @RequestMapping(value = "/students", method = RequestMethod.GET)
  public ResponseEntity<Page<StudentDTO>> getAllStudents(int pageNumber, int pageSize){
    Page<StudentDTO> paginatedStudents = studentService.getPaginatedStudents(pageNumber, pageSize);
    return ResponseEntity.ok(paginatedStudents);
  }
  @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
  public StudentDTO findStudent(@PathVariable("id") Long id){
    return studentService.findByIDDTO(id);
  }

  @RequestMapping(value = "/students/", method = RequestMethod.POST)
  public ResponseEntity createCourse(@Valid @RequestBody  StudentDTO studentInput){
    Student student = studentService.convertToStudent(studentInput);
    studentService.save(student);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/students/", method = RequestMethod.PUT)
  public ResponseEntity updateCourse(@Valid@RequestBody StudentDTO studentInput){
    Student student = studentService.convertToStudent(studentInput);
    studentService.save(student);
    return new ResponseEntity(HttpStatus.ACCEPTED);
  }
  @RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
  public void deleteStudentById(@PathVariable("studentId") Long id){

    studentService.deleteByID(id);
  }
}
