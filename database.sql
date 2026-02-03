CREATE DATABASE belajar_java_persistence_api;

USE belajar_java_persistence_api;

CREATE TABLE customers
(
    id   VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE InnoDB;

ALTER TABLE customers
    ADD COLUMN primary_email VARCHAR(150);

ALTER TABLE customers
ADD COLUMN age TINYINT,
    ADD COLUMN married BOOLEAN;

ALTER TABLE customers
ADD COLUMN type VARCHAR(50);

SELECT * FROM customers;


CREATE TABLE categories
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500)
) ENGINE InnoDB;

ALTER TABLE categories
ADD COLUMN created_at TIMESTAMP,
    ADD COLUMN updated_at TIMESTAMP;

SELECT * FROM categories;


CREATE TABLE images
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    image      MEDIUMBLOB
) ENGINE InnoDB;

SELECT * FROM images;

DROP TABLE images;


CREATE TABLE members
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(100) NOT NULL,
    title       VARCHAR(100),
    first_name  VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name   VARCHAR(100)
) ENGINE InnoDB;

SELECT * FROM members;
