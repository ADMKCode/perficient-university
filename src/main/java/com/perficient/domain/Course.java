package com.perficient.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
//@Builder
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private Long courseId;
  private String name;
  private String description;
  private int credits;
  private String area;
  private double price;
  /*
  @ManyToMany
  @JoinTable(name = "register",
      joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private Set<Student> students = new HashSet<>();

   */
  @OneToMany(mappedBy = "course")
  private List<Register> registers;

  /*
  public Course(Long courseId, String name, String description, int credits, Area area, double price) {
    this.courseId = courseId;
    this.name = name;
    this.description = description;
    this.credits = credits;
    this.area = area;
    this.price = price;
  }

   */
}
