package com.moviesservices.moviesreviewservice.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.moviesservices.moviesreviewservice.handler.ReviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ReviewRouter {

  @Bean
  public RouterFunction<ServerResponse> reviewsRoute(ReviewHandler reviewHandler) {

    return route()
        .nest(
            path("v1/review"),
            builder -> { // this the way of nested when two api path are same in one
              builder
                  .POST("", reviewHandler::createReview)
                  .GET("", reviewHandler::getReviews)
                  .PUT("{id}", reviewHandler::updateReview)
                  .DELETE("{id}", reviewHandler::deleteReview);
            })
        //                .POST("", reviewHandler::createReview)
        //                .GET("", reviewHandler::getReviews)
        .build();
  }
}
