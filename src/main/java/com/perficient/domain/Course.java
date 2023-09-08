package com.perficient.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

  @OneToMany(mappedBy = "course")
  private List<Register> registers;
}
