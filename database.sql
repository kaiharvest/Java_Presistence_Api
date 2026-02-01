CREATE DATABASE belajar_java_persistence_api;

USE belajar_java_persistence_api;

CREATE TABLE customers
(
    id   VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE InnoDB;


ALTER TABLE customers
    ADD COLUMN primary_email VARCHAR(150);

SELECT * FROM customers;