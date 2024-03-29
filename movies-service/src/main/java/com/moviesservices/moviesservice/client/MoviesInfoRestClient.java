package com.moviesservices.moviesservice.client;

import com.moviesservices.moviesservice.model.MovieInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MoviesInfoRestClient {

  private WebClient webClient;

  public MoviesInfoRestClient(WebClient webClient) {
    this.webClient = webClient;
  }

  @Value("${restClient.moviesInfoUrl}")
  private String moviesInfoUrl;

  public Mono<MovieInfo> retrieveMovieInfo(String movieId) {

    var url = moviesInfoUrl.concat("/{id}");

    return webClient.get().uri(url, movieId).retrieve().bodyToMono(MovieInfo.class).log();
  }
}
