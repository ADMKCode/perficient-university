package com.perficient.services.springdatajpa;

import com.perficient.domain.Student;
import com.perficient.domain.repositories.CourseRepository;
import com.perficient.domain.repositories.RegisterRepository;
import com.perficient.domain.repositories.StudentRepository;
import com.perficient.services.StudentService;
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

  @Override
  public Student findByID(Long aLong) {
    return studentRepository.findById(aLong).orElse(null);
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
