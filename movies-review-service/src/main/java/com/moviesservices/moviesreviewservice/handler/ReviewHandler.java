package com.moviesservices.moviesreviewservice.handler;

import com.moviesservices.moviesreviewservice.exception.ReviewDataException;
import com.moviesservices.moviesreviewservice.model.Review;
import com.moviesservices.moviesreviewservice.repository.ReviewReactiveRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReviewHandler {

  // NOTE: functional programming not work with @Valid to validate the class
  // for that one use "validator"

  @Autowired private Validator validator;

  private final ReviewReactiveRepository reviewReactiveRepository;

  public Mono<ServerResponse> createReview(ServerRequest request) {

    return request
        .bodyToMono(Review.class)
        .doOnNext(this::validate)
        .flatMap(reviewReactiveRepository::save)
        .flatMap(savedReview -> ServerResponse.status(HttpStatus.CREATED).bodyValue(savedReview));
  }

  private void validate(Review review) {
    var constraintViolations = validator.validate(review);
    log.info("constraintViolations : {}", constraintViolations);
    if (!constraintViolations.isEmpty()) {
      var errorMessage =
          constraintViolations.stream()
              .map(ConstraintViolation::getMessage)
              .sorted()
              .collect(Collectors.joining(","));

      throw new ReviewDataException(errorMessage);
    }
  }

  public Mono<ServerResponse> getReviews(ServerRequest serverRequest) {

    var movieInfoId = serverRequest.queryParam("movieInfoId");
    if (movieInfoId.isPresent()) {
      var reviewFlux =
          reviewReactiveRepository.findByMovieInfoId(Long.parseLong(movieInfoId.get()));
      return ServerResponse.ok().body(reviewFlux, Review.class);
    }

    var reviewFlux = reviewReactiveRepository.findAll();
    return ServerResponse.ok().body(reviewFlux, Review.class);
  }

  // TODO update into mapper to DTO and Entity
  public Mono<ServerResponse> updateReview(ServerRequest serverRequest) {

    var reviewId = serverRequest.pathVariable("id");

    var existingReview = reviewReactiveRepository.findById(reviewId);
    // Second way to handle null response with global exception.
    //                .switchIfEmpty(Mono.error(new ReviewNotFoundException("Review not found with
    // id : " + reviewId)));

    return existingReview
        .flatMap(
            review ->
                serverRequest
                    .bodyToMono(Review.class)
                    .map(
                        request -> {
                          review.setComment(request.getComment());
                          review.setRating(request.getRating());
                          return review;
                        })
                    .flatMap(reviewReactiveRepository::save)
                    .flatMap(savedReview -> ServerResponse.ok().bodyValue(savedReview)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> deleteReview(ServerRequest serverRequest) {

    var reviewId = serverRequest.pathVariable("id");
    return reviewReactiveRepository.deleteById(reviewId).then(ServerResponse.noContent().build());
  }
}
