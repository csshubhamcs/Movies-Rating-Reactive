package com.moviesservices.moviesreviewservice.repository;


import com.moviesservices.moviesreviewservice.model.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review, String> {

    Flux<Review> findByMovieInfoId(long l);
}
