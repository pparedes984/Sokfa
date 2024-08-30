package com.example.sofka.AccountTransaction.model.dto;

public class ClientDTO {
    private Long clientId;
    private String name;
    private String gender;
    private int age;
    private String dni;
    private String address;
    private String telephone;
    private String state;

    // Constructor sin parámetros
    public ClientDTO() {}

    // Constructor con parámetros
    public ClientDTO(Long clientId, String name, String gender, int age, String dni, String address, String telephone, String state) {
        this.clientId = clientId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dni = dni;
        this.address = address;
        this.telephone = telephone;
        this.state = state;
    }

    // Getters y Setters

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

