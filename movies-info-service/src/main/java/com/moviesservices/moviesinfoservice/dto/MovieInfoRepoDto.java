package com.moviesservices.moviesinfoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieInfoRepoDto {
  private String movieInfoId;
  private String name;
  private Integer year;
  private List<String> cast;
  private LocalDate releaseDate;
}
