package com.moviesservices.moviesinfoservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieInfo {

  @Id private String movieInfoId;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotNull
  @Positive(message = "Year should be positive")
  private Integer year;

  private List<String> cast;
  private LocalDate releaseDate;
}
