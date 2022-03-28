package com.example.reactivecircuitbrealer;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import java.time.Duration;


@SpringBootApplication
public class ReactiveCircuitBrealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveCircuitBrealerApplication.class, args);

    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> customerServiceCusomtizer() {
        return factory -> {
            factory.configure(builder -> builder
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "customer-service");
        };
    }

}
