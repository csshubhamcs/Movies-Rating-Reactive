package com.moviesservices.moviesservice.client;

import com.moviesservices.moviesservice.model.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@Component
public class ReviewsRestClient {
  private WebClient webClient;

  public ReviewsRestClient(WebClient webClient) {
    this.webClient = webClient;
  }

  @Value("${restClient.reviewUrl}")
  private String reviewUrl;

  public Flux<Review> retrieveReviews(String movieInfoId) {
    //        var url = reviewUrl.concat("/{movieInfoId}");
    var url =
        UriComponentsBuilder.fromHttpUrl(reviewUrl) // Alternate Way to create URL
            .queryParam("movieInfoId", movieInfoId)
            .buildAndExpand()
            .toUriString();
    return webClient
        .get()
        //                .uri(url,movieInfoId)
        .uri(url) // Alternate way
        .retrieve()
        .bodyToFlux(Review.class);
  }
}
