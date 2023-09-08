package com.perficient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.perficient.domain.Area;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
  private Long courseId;
  @NotNull
  @Size(min=3, max=100)
  @JsonProperty
  private String name;

  @Min(1)
  @JsonProperty
  private int credits;

  @Enumerated(value = EnumType.STRING)
  @NotNull
  @JsonProperty
  private String area;

  @NotNull
  @JsonProperty
  private String description;

}
