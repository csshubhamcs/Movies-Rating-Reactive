package com.moviesservices.moviesservice.controller;

import com.moviesservices.moviesservice.client.MoviesInfoRestClient;
import com.moviesservices.moviesservice.client.ReviewsRestClient;
import com.moviesservices.moviesservice.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/movies")
public class MoviesController {

  public MoviesController(
      MoviesInfoRestClient moviesInfoRestClient, ReviewsRestClient reviewsRestClient) {
    this.moviesInfoRestClient = moviesInfoRestClient;
    this.reviewsRestClient = reviewsRestClient;
  }

  private MoviesInfoRestClient moviesInfoRestClient;
  private ReviewsRestClient reviewsRestClient;

  @GetMapping("{id}")
  public Mono<Movie> retrieveMovieById(@PathVariable("id") String movieId) {

    return moviesInfoRestClient
        .retrieveMovieInfo(movieId)
        .flatMap(
            movieInfo -> {
              var reviewListMono = reviewsRestClient.retrieveReviews(movieId).collectList();
              return reviewListMono.map(reviews -> new Movie(movieInfo, reviews));
            });
  }
}
