package com.perficient.services.springdatajpa;

import com.perficient.Exceptions.BusinessException;
import com.perficient.Exceptions.CustomException;
import com.perficient.Exceptions.NotFoundException;
import com.perficient.domain.Course;
import com.perficient.domain.repositories.CourseRepository;
import com.perficient.dto.CourseDTO;
import com.perficient.services.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseSDJpaService implements CourseService {
  private final CourseRepository courseRepository;
  public CourseSDJpaService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }
  @Override
  public Set<Course> findAll() {
    Set<Course> courses = new HashSet<>();
    courseRepository.findAll().forEach(courses::add);
    return courses;
  }
  public Page<CourseDTO> getPaginatedCourses(int numeroPagina, int tamanoPagina) {
    Pageable pageable = PageRequest
        .of(numeroPagina, tamanoPagina, Sort.by("name")
            .ascending());
    Page<Course> coursePage = courseRepository.findAll(pageable);
    return coursePage.map(this::convertToDTO);
  }
  public CourseDTO convertToDTO(Course course) {
    try{
      validate(course);
      CourseDTO courseDTO = CourseDTO.builder().courseId(course.getCourseId())
          .name(course.getName())
          .description(course.getDescription())
          .credits(course.getCredits())
          .area(course.getArea())
          .build();
      return courseDTO;
    }catch (CustomException e){
      System.out.println("Error en el convertidor:::"+e.getMessage());
      return null;
    }
  }
  public Course convertToCourse(CourseDTO courseDTO) {
    Course course = new Course();
    try{
      validate(courseDTO);
      course.setCourseId(courseDTO.getCourseId());
      course.setName(courseDTO.getName());
      course.setDescription(courseDTO.getDescription());
      course.setCredits(courseDTO.getCredits());
      course.setArea(courseDTO.getArea());
    }
    catch(CustomException e){
      throw e;
    }
    return course;
  }
  public CourseDTO validate(CourseDTO course) throws CustomException {
    if(course.getCourseId() != null){
      this.findByID(course.getCourseId());
    }
    if (course.getName() == null || course.getName().isEmpty()) {
      throw new CustomException("P-401", "The name of the course is invalid");
    }
    if (course.getDescription() == null || course.getDescription().isEmpty()) {
      throw new CustomException("P-402", "The description of the course is invalid");
    }
    if(course.getCredits() > 21){
      throw new BusinessException("P-300", HttpStatus.INTERNAL_SERVER_ERROR, "La cantidad de créditos no es permitida");
    }
    else {
      return course;
    }
  }
  public Course validate(Course course) throws CustomException {
    if (course.getName() == null || course.getName().isEmpty()) {
      throw new CustomException("P-401", "The name of the course is invalid");
    }
    if (course.getDescription() == null || course.getDescription().isEmpty()) {
      throw new CustomException("P-402", "The description of the course is invalid");
    }
    if(course.getCredits() == 0){
      throw new BusinessException("P-300", HttpStatus.INTERNAL_SERVER_ERROR, "La cantidad de créditos no puede ser 0");
    }
    else {
      return course;
    }
  }
  @Override
  public Course findByID(Long aLong) {
    Optional<Course> courseOptional = courseRepository.findById(aLong);
    if (!courseOptional.isPresent()) {
      throw new NotFoundException("Course Not Found");
    }
    return courseOptional.get();
  }
  public CourseDTO findByIDDTO(Long aLong) {
    Course course = findByID(aLong);
    return convertToDTO(course);
  }
  @Override
  public Course save(Course object) {
    return courseRepository.save(object);
  }
  public CourseDTO saveDTO(CourseDTO object) {
    Course course = convertToCourse(object);
    Course courseReturned = save(course);
    return convertToDTO(courseReturned);
  }
  @Override
  public void delete(Course object) {
    courseRepository.delete(object);
  }
  @Override
  public void deleteByID(Long aLong) {
    courseRepository.deleteById(aLong);
  }
}
