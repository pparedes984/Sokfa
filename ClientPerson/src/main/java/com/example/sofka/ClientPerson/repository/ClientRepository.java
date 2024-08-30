package com.example.sofka.ClientPerson.repository;

import com.example.sofka.ClientPerson.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

