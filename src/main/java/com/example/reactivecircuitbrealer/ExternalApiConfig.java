package com.example.reactivecircuitbrealer;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Configuration
public class ExternalApiConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:4012");
    }

    @Bean
    public CircuitBreakerConfigCustomizer externalServiceCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer
                .of("externalService",
                        builder -> builder.slidingWindowSize(10)
                                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(5))
                                .minimumNumberOfCalls(5)
                                .failureRateThreshold(50.0f)
                );
    }
}
