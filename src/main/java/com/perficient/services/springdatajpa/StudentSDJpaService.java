package com.perficient.services.springdatajpa;

import com.perficient.Exceptions.BusinessException;
import com.perficient.Exceptions.CustomException;
import com.perficient.domain.Student;
import com.perficient.domain.repositories.StudentRepository;
import com.perficient.dto.StudentDTO;
import com.perficient.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentSDJpaService implements StudentService {
  private final StudentRepository studentRepository;

  public StudentSDJpaService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public Set<Student> findAll() {
    Set<Student> students = new HashSet<>();
    studentRepository.findAll().forEach(students::add);
    return students;
  }

  public Page<StudentDTO> getPaginatedStudents(int numeroPagina, int tamanoPagina) {
    Pageable pageable = PageRequest
        .of(numeroPagina, tamanoPagina, Sort.by("name")
            .ascending());
    Page<Student> studentPage = studentRepository.findAll(pageable);
    return studentPage.map(this::convertToDTO);
  }

  public StudentDTO convertToDTO(Student student) {
    try{
      validate(student);
      StudentDTO studentDTO = StudentDTO.builder().studentId(student.getStudentId())
          .firstName(student.getFirstName())
          .complementaryName(student.getComplementaryName())
          .lastName(student.getLastName())
          .di(student.getDi())
          .diType(student.getDiType())
          .build();
      return studentDTO;
    }catch (CustomException e){
      System.out.println("Error en el convertidor:::"+e.getMessage());
      return null;
    }
  }

  public Student convertToStudent(StudentDTO studentDTO) {
    Student student = new Student();
    try{
      validate(studentDTO);
      student.setStudentId(studentDTO.getStudentId());
      student.setFirstName(studentDTO.getFirstName());
      student.setLastName(studentDTO.getLastName());
      student.setComplementaryName(studentDTO.getComplementaryName());
      student.setEmail(studentDTO.getEmail());
      student.setBirthDate(studentDTO.getBirthDate());
    }
    catch(CustomException e){
      throw e;
    }
    return student;
  }

  public Student validate(Student student) throws CustomException {
    if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
      throw new CustomException("P-401", "The name of the student is invalid");
    }
    if (student.getLastName() == null || student.getLastName().isEmpty()) {
      throw new CustomException("P-402", "The lastname of the student is invalid");
    }
    if(student.getStudentId() == null || student.getStudentId() == 0){
      throw new BusinessException("P-300", HttpStatus.INTERNAL_SERVER_ERROR, "The student id is invalid");
    }
    else {
      return student;
    }
  }

  public StudentDTO validate(StudentDTO student) throws CustomException {
    if(student.getStudentId() != null){
      this.findByID(student.getStudentId());
    }
    if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
      throw new CustomException("P-401", "The name of the course is invalid");
    }
    if (student.getLastName() == null || student.getLastName().isEmpty()) {
      throw new CustomException("P-402", "The lastname of the student is invalid");
    }
    else {
      return student;
    }
  }

  @Override
  public Student findByID(Long aLong) {
    return studentRepository.findById(aLong).orElse(null);
  }

  public StudentDTO findByIDDTO(Long aLong) {
    Student student = findByID(aLong);
    return convertToDTO(student);
  }

  @Override
  public Student save(Student object) {
    return studentRepository.save(object);
  }

  @Override
  public void delete(Student object) {
    studentRepository.delete(object);
  }

  @Override
  public void deleteByID(Long aLong) {
    studentRepository.deleteById(aLong);
  }
}
