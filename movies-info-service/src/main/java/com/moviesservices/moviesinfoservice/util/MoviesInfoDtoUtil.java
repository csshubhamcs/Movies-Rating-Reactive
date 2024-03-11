package com.moviesservices.moviesinfoservice.util;


import com.moviesservices.moviesinfoservice.domain.MovieInfo;
import com.moviesservices.moviesinfoservice.dto.MovieInfoDto;
import org.springframework.beans.BeanUtils;

public class MoviesInfoDtoUtil {

  public static MovieInfoDto toDto(MovieInfo movieInfo) {
    MovieInfoDto movieInfoDto = new MovieInfoDto();

    BeanUtils.copyProperties(movieInfo, movieInfoDto);
    return movieInfoDto;
  }

  public static MovieInfo fromDto(MovieInfoDto movieInfoDto) {

    MovieInfo movieInfo = new MovieInfo();

    BeanUtils.copyProperties(movieInfoDto, movieInfo);
    return movieInfo;
  }
}
