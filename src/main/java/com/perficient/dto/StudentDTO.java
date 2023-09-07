package com.perficient.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.perficient.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
  private Long id;
  @NotNull
  @Size(min=3, max=100)
  private String firstName;

  private String complementaryName;

  @NotNull
  @Size(min=3, max=100)
  private String lastName;

  @Enumerated(value = EnumType.STRING)
  @NotNull
  private DiType diType;

  @NotNull
  @Size(min=3, max=10)
  private String di;
}
