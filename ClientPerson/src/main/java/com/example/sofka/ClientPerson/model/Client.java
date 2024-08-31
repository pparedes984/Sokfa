package com.example.sofka.ClientPerson.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "clientId")
public class Client extends Person{

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private ClientState state;


    public Client(String name, String gender, int age, String dni, String address, String telephone, String password, ClientState state) {
        super(name, gender, age, dni, address, telephone);
        this.password = password;
        this.state = state;
    }

    public Client() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientState isState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }

    public enum ClientState{
        ACTIVO,
        INACTIVO;
    }
}

