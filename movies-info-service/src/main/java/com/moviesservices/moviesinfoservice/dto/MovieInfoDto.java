package com.moviesservices.moviesinfoservice.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieInfoDto {
  private String movieInfoId;
  private String name;
  private Integer year;
  private List<String> cast;
  private LocalDate releaseDate;
}
