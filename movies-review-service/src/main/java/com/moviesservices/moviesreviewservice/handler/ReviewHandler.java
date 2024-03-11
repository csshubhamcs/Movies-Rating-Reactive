package com.moviesservices.moviesreviewservice.handler;


import com.moviesservices.moviesreviewservice.model.Review;
import com.moviesservices.moviesreviewservice.repository.ReviewReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReviewHandler {

    private final ReviewReactiveRepository reviewReactiveRepository;

    public Mono<ServerResponse> createReview(ServerRequest request) {

       return  request.bodyToMono(Review.class)
               .flatMap( reviewReactiveRepository::save )
               .flatMap(savedReview -> ServerResponse.status(HttpStatus.CREATED).bodyValue(savedReview));

    }

    public Mono<ServerResponse> getReviews(ServerRequest serverRequest) {

        var reviewFlux = reviewReactiveRepository.findAll();
        return ServerResponse.ok().body(reviewFlux, Review.class);
    }
}
