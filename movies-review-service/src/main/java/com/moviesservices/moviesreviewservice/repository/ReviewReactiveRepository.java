package com.moviesservices.moviesreviewservice.repository;


import com.moviesservices.moviesreviewservice.model.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review, String> {

}
