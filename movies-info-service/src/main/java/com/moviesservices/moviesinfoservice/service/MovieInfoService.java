package com.moviesservices.moviesinfoservice.service;


import com.moviesservices.moviesinfoservice.dto.MovieInfoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieInfoService {

  public Mono<MovieInfoDto> addMovieInfo(MovieInfoDto movieInfoDto);

  public Flux<MovieInfoDto> getAllMoviesInfo();

  Mono<MovieInfoDto> getMovieInfoById(String id);

  Mono<MovieInfoDto> updateMovieInfo(MovieInfoDto updateMovieInfoDto, String id);

  Mono<Void> deleteMovieInfo(String id);

  Flux<MovieInfoDto> getMovieInfoByYear(Integer year);
}
