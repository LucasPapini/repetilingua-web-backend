CREATE TABLE tb_text (
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    title VARCHAR(255),
    module VARCHAR(255),
    completed BOOLEAN,
    moment TIMESTAMP,
    PRIMARY KEY (id)
);