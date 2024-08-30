package com.example.sofka.AccountTransaction.service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import com.example.sofka.AccountTransaction.model.dto.ClientDTO;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceClient {

    private final WebClient webClient;

    public ClientServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/clientes").build();
    }

    public Mono<ClientDTO> getClientById(Long clientId) {

        return this.webClient.get()
                .uri("/{id}", clientId)
                .retrieve()
                .bodyToMono(ClientDTO.class);
    }
}

