CREATE DATABASE belajar_java_persistence_api;

USE belajar_java_persistence_api;

# table customers
CREATE TABLE customers
(
    id   VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE InnoDB;

ALTER TABLE customers
    ADD COLUMN primary_email VARCHAR(150);

SELECT * FROM customers;


# table categories
CREATE TABLE categories
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500)
) ENGINE InnoDB;

SELECT * FROM categories;


# table images
CREATE TABLE images
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    image      MEDIUMBLOB
) ENGINE InnoDB;

SELECT * FROM images;

DROP TABLE images;


# table members
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
SELECT * FROM members WHERE id = 1;


# table departments
CREATE TABLE departments
(
    company_id    VARCHAR(100) NOT NULL,
    department_id VARCHAR(100) NOT NULL,
    name          VARCHAR(150) NOT NULL,
    PRIMARY KEY (company_id, department_id)
) ENGINE InnoDB;

SELECT * FROM departments;


# table hobbies
CREATE TABLE hobbies
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT          NOT NULL,
    name      VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_members_hobbies (member_id) REFERENCES members (id)
) ENGINE InnoDB;

DROP TABLE hobbies;

SELECT * FROM hobbies;


# table skills
CREATE TABLE skills
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT          NOT NULL,
    name      VARCHAR(100) NOT NULL,
    value     INT          NOT NULL,
    FOREIGN KEY fk_members_skills (member_id) REFERENCES members (id),
    CONSTRAINT skills_unique UNIQUE (member_id, name)
) ENGINE InnoDB;

SELECT * FROM skills;


# table credential
CREATE TABLE credential
(
    id       VARCHAR(100) NOT NULL PRIMARY KEY,
    email    VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL
) ENGINE InnoDB;

SELECT * FROM credential;


# table users
CREATE TABLE users
(
    id   VARCHAR(100) NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
) ENGINE InnoDB;

SELECT * FROM users;


# wallet
CREATE TABLE wallet
(
    id      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    balance BIGINT      NOT NULL,
    FOREIGN KEY fk_users_wallet (user_id) REFERENCES users (id)
) ENGINE InnoDB;

SELECT * FROM wallet;


# table brands
CREATE TABLE brands
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500)
);

SELECT * FROM brands;


# table productions
CREATE TABLE products
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    brand_id    VARCHAR(100) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    price       BIGINT       NOT NULL,
    description VARCHAR(500),
    FOREIGN KEY fk_brand_products (brand_id) REFERENCES brands (id)
);

SELECT * FROM products;

SHOW TABLES;