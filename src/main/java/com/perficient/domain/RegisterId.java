package com.perficient.domain;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class RegisterId implements Serializable {
  private Long studentId;
  private Long courseId;

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }
}
