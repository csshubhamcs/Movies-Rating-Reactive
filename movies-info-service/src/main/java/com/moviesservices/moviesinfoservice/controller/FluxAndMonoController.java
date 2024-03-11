package com.moviesservices.moviesinfoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learning Flux and Mono.
 *
 * @author shubham
 */
@RestController
public class FluxAndMonoController {

  /**
   * Just Testing .
   *
   * @return Integer of return value
   */
  @GetMapping("/flux")
  public Flux<Integer> flux() {

    return Flux.just(1, 2, 3);
  }

  @GetMapping("/mono")
  public Mono<Integer> mono() {

    return Mono.just(10);
  }
}
