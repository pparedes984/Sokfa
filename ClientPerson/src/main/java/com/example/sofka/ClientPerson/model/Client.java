package com.example.sofka.ClientPerson.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "clientId")
public class Client extends Person{

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean state;


    public Client(String name, String gender, int age, String dni, String address, String telephone, String password, Boolean state) {
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

    public Boolean isState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}

