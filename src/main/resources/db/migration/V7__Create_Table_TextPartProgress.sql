CREATE TABLE tb_text_part_progress (
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    text_part_id BIGINT NOT NULL,
    d1_read_listen INT,
    d1_listen_only INT,
    d1_final_check INT,
    d2_read_listen INT,
    d2_listen_only INT,
    d2_final_check INT,
    last_activity_at TIMESTAMP,
    complete BOOLEAN,
    PRIMARY KEY (id)
);