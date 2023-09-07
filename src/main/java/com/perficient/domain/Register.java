package com.perficient.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "registers")
public class Register {

  @EmbeddedId
  private RegisterId id;

  @ManyToOne
  @JoinColumn(name = "courseId", insertable = false, updatable = false)
  private Course course;

  @ManyToOne
  @JoinColumn(name = "studentId", insertable = false, updatable = false)
  private Student student;

  private LocalDateTime localDateTime;

  public RegisterId getId() {
    return id;
  }

  public void setId(RegisterId id) {
    this.id = id;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }
}
