package com.perficient.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long studentId;

  private String username;
  private String firstName;
  private String complementaryName;
  private String lastName;
  private DiType diType;
  @Column(unique = true)
  @Size(min=10, max=10)
  @NotNull
  private String di;
  /*
  @ManyToMany(mappedBy = "students")
  private Set<Course> courses = new HashSet<>();
  */

  @OneToMany(mappedBy = "student")
  private List<Register> registers;
  @Lob
  private Byte[] image;

  @Column(unique = true)
  @Email
  private String email;

  private LocalDate birthDate;
}
