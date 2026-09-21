CREATE TABLE tb_user (
    id BIGINT AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (id)
);