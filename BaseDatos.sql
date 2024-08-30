use sofka;
-- Crear tabla Persona
CREATE TABLE Person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender ENUM('MASCULINO', 'FEMENINO') NOT NULL,
    age INT NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255),
    telephone VARCHAR(20)
);

-- Crear tabla Cliente que hereda de Persona
CREATE TABLE Client (
    clientId BIGINT AUTO_INCREMENT PRIMARY KEY,
    personId BIGINT,
    password VARCHAR(255) NOT NULL,
    state ENUM('ACTIVO', 'INACTIVO') NOT NULL,
    FOREIGN KEY (personId) REFERENCES Person(id)
);

-- Crear tabla Cuenta
CREATE TABLE Account (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountNumber VARCHAR(255) NOT NULL,
    accountType ENUM('AHORROS', 'CORRIENTE') NOT NULL,
    openingBalance DECIMAL(15, 2) NOT NULL,
    state ENUM('ACTIVA', 'INACTIVA') NOT NULL,
    clientId BIGINT, 
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

-- Crear tabla Movimiento
CREATE TABLE Transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transactionType ENUM('CREDITO', 'DEBITO') NOT NULL,
    value DECIMAL(15, 2) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    accountId BIGINT,
    FOREIGN KEY (accountId) REFERENCES Account(id)
);