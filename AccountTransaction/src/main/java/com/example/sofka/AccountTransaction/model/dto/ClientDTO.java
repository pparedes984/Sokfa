package com.example.sofka.AccountTransaction.model.dto;

public class ClientDTO {
    private Long client_id;
    private String name;
    private String gender;
    private int age;
    private String dni;
    private String address;
    private String telephone;
    private String state;

    private String password;

    // Constructor sin parámetros
    public ClientDTO() {}

    // Constructor con parámetros
    public ClientDTO(Long client_id, String name, String gender, int age, String dni, String address, String telephone, String state, String password) {
        this.client_id = client_id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dni = dni;
        this.address = address;
        this.telephone = telephone;
        this.state = state;
        this.password = password;
    }

    // Getters y Setters

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

