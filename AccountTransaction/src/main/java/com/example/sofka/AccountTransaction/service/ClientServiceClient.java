package com.example.sofka.AccountTransaction.service;

import com.example.sofka.AccountTransaction.model.dto.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class ClientServiceClient {

    private final WebClient webClient;

    public ClientServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<Boolean> verifyClientExists(Long clientId) {
        return webClient.get()
                .uri("/clientes/{id}", clientId)
                .retrieve()
                .bodyToMono(ClientDTO.class)
                .map(client -> true)
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientResponseException.NotFound) {
                        System.err.println("Client not found: " + clientId);
                        return Mono.just(false);
                    } else {
                        System.err.println("Error communicating with ClientPerson service: " + ex.getMessage());
                        return Mono.error(ex);
                    }
                });
    }
}
