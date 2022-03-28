package com.example.reactivecircuitbrealer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ExternalApi externalApi;

    @GetMapping("/users")
    public Mono<Object> get() {
        System.out.println("cc");
        return externalApi.getExternalUser();
    }
}
