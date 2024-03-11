package com.moviesservices.moviesinfoservice.service;

import com.moviesservices.moviesinfoservice.domain.MovieInfo;
import com.moviesservices.moviesinfoservice.dto.MovieInfoDto;
import com.moviesservices.moviesinfoservice.dto.MovieInfoMapper;
import com.moviesservices.moviesinfoservice.repository.MovieInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieInfoServiceImpl implements MovieInfoService {
  private final MovieInfoRepository movieInfoRepository;
  private final MovieInfoMapper movieInfoMapper;

  @Override
  public Mono<MovieInfoDto> addMovieInfo(MovieInfoDto movieInfoDto) {
    MovieInfo movieInfo = movieInfoMapper.fromDto(movieInfoDto);
    return movieInfoRepository.save(movieInfo).map(movieInfoMapper::toDto);
  }

  @Override
  public Flux<MovieInfoDto> getAllMoviesInfo() {

    return movieInfoRepository.findAll().map(movieInfoMapper::toDto);
  }

  @Override
  public Mono<MovieInfoDto> getMovieInfoById(String id) {
    return movieInfoRepository.findById(id).map(movieInfoMapper::toDto);
  }

  @Override
  public Mono<MovieInfoDto> updateMovieInfo(MovieInfoDto updateMovieInfoDto, String id) {

    Mono<MovieInfo> movieInfoMono = movieInfoRepository.findById(id);

    return movieInfoMono
        .flatMap(
            movieInfo -> {
              movieInfoMapper.merge(updateMovieInfoDto, movieInfo);
              return movieInfoRepository.save(movieInfo);
            })
        .map(movieInfoMapper::toDto);
  }

  @Override
  public Mono<Void> deleteMovieInfo(String id) {
    return movieInfoRepository.deleteById(id);
  }

  @Override
  public Flux<MovieInfoDto> getMovieInfoByYear(Integer year) {
    return movieInfoRepository.findByYear(year).map(movieInfoMapper::toDto);
  }
}
