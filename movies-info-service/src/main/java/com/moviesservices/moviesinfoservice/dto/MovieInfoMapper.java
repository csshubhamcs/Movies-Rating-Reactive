package com.moviesservices.moviesinfoservice.dto;

import com.moviesservices.moviesinfoservice.domain.MovieInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.ComponentScan;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@ComponentScan
public interface MovieInfoMapper {

  MovieInfoDto toDto(MovieInfo movieInfo);

  MovieInfo fromDto(MovieInfoDto movieInfoDto);

  void merge(MovieInfoDto movieInfoDto, @MappingTarget MovieInfo movieInfo);
}
