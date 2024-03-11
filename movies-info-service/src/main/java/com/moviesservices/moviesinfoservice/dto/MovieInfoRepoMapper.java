package com.moviesservices.moviesinfoservice.dto;

import com.moviesservices.moviesinfoservice.domain.MovieInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface MovieInfoRepoMapper {

  MovieInfoRepoDto toRepo(MovieInfo movieInfo);

  MovieInfo fromRepo(MovieInfoRepoDto movieInfoRepoDto);

  void merge(MovieInfo movieInfo, @MappingTarget MovieInfoRepoDto movieInfoRepoDto);
}
