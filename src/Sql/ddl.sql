CREATE DATABASE IF NOT EXISTS daycare;

USE daycare;

CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(30) NOT NULL
    );

CREATE TABLE IF NOT EXISTS employees(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    morning BOOLEAN NOT NULL,
    afternoon BOOLEAN NOT NULL,
    phone_number INT NOT NULL,
    e_mail VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS parents(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number INT NOT NULL,
    e_mail VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS children(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    extra_info VARCHAR(500) NOT NULL,
    parent_id INT NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES parents(id)
    );

CREATE TABLE IF NOT EXISTS children_list(
     child_id INT NOT NULL,
     FOREIGN KEY (child_id) REFERENCES children(id)
    );