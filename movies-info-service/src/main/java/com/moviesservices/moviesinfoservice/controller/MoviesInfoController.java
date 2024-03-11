package com.moviesservices.moviesinfoservice.controller;


import com.moviesservices.moviesinfoservice.dto.MovieInfoDto;
import com.moviesservices.moviesinfoservice.service.MovieInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MoviesInfoController {
  private final MovieInfoService movieInfoService;

  @PostMapping("/movieInfos")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<MovieInfoDto> addMovieInfo(@RequestBody @Valid MovieInfoDto movieInfoDto) {
    return movieInfoService.addMovieInfo(movieInfoDto);
  }

  @GetMapping("/movieInfos")
  public Flux<MovieInfoDto> getAllMovieInfos(
      @RequestParam(value = "year", required = false) Integer year) {

    if (year != null) {
      return movieInfoService.getMovieInfoByYear(year);
    }

    return movieInfoService.getAllMoviesInfo();
  }

  @GetMapping("/movieInfos/{id}")
  public Mono<ResponseEntity<MovieInfoDto>> getMovieInfosById(@PathVariable String id) {
    return movieInfoService
        .getMovieInfoById(id)
        .map(movieInfoDto -> ResponseEntity.ok().body(movieInfoDto))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @PatchMapping("/movieInfos/{id}")
  public Mono<ResponseEntity<MovieInfoDto>> updateMovieInfos(
      @RequestBody MovieInfoDto updateMovieInfoDto, @PathVariable String id) {
    return movieInfoService
        .updateMovieInfo(updateMovieInfoDto, id)
        .map(movieInfoDto -> ResponseEntity.ok().body(movieInfoDto))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @DeleteMapping("/movieInfos/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteMovieInfo(@PathVariable String id) {
    return movieInfoService.deleteMovieInfo(id);
  }
}
