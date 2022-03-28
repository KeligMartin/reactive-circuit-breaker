package com.example.reactivecircuitbrealer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ExternalApi {

    private final WebClient webClient;

    @CircuitBreaker(name = "externalService", fallbackMethod = "fallback")
    public Mono<Object> getExternalUser() {
        return webClient.get().uri("/users").retrieve().bodyToMono(Object.class);
    }

    public Mono<String> fallback(RuntimeException e) {
        return Mono.just("service unavailable");
    }
}
