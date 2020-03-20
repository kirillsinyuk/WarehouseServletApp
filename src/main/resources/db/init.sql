CREATE DATABASE IF NOT EXISTS warehouse
    COLLATE utf8_general_ci;

USE warehouse;

DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Receipt;
DROP TABLE IF EXISTS Movement;
DROP TABLE IF EXISTS Sale;
DROP TABLE IF EXISTS Warehouse;

CREATE TABLE IF NOT EXISTS Warehouse
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name varchar(255),

    PRIMARY KEY (id)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Receipt
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    warehouse_id BIGINT(20),

    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse (id)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Sale
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    warehouse_id BIGINT(20),

    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse (id)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Movement
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    warehouse_from BIGINT(20),
    warehouse_to BIGINT(20),

    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_from) REFERENCES Warehouse (id),
    FOREIGN KEY (warehouse_to) REFERENCES Warehouse (id)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS Product
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    vendorCode varchar(255) UNIQUE,
    name varchar(255),
    purchasePrice BIGINT(20),
    sellingPrice BIGINT(20),
    warehouse_id BIGINT(20),
    receipt_id BIGINT(20),
    sale_id BIGINT(20),
    movement_id BIGINT(20),

    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse (id),
    FOREIGN KEY (receipt_id) REFERENCES Receipt (id),
    FOREIGN KEY (sale_id) REFERENCES Sale (id),
    FOREIGN KEY (movement_id) REFERENCES Movement (id)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

