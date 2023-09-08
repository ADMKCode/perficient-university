package com.perficient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.perficient.domain.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
  private Long studentId;
  @NotNull
  @Size(min=3, max=100)
  @JsonProperty
  private String firstName;

  private String complementaryName;

  @NotNull
  @Size(min=3, max=100)
  @JsonProperty
  private String lastName;

  @Enumerated(value = EnumType.STRING)
  @NotNull
  @JsonProperty
  private String diType;

  @NotNull
  @Size(min=3, max=10)
  @JsonProperty
  private String di;

  @NotNull
  @Size(min=10, max=100)
  @JsonProperty
  private String email;

  @NotNull
  @JsonProperty
  private LocalDate birthDate;
}
