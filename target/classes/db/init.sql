CREATE DATABASE IF NOT EXISTS warehouse
    COLLATE utf8_general_ci;

USE warehouse;

DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Warehouse;

CREATE TABLE IF NOT EXISTS Warehouse
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name varchar(255),
    PRIMARY KEY (id)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Product
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    vendorCode varchar(255),
    name varchar(255),
    purchasePrice BIGINT(20),
    sellingPrice BIGINT(20),
    warehouse_id BIGINT(20),
    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse (id)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
