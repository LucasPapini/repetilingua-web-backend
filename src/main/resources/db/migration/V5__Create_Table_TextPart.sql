CREATE TABLE tb_text_part (
    id BIGINT AUTO_INCREMENT,
    text_id BIGINT,
    part_number INTEGER,
    content VARCHAR(255),
    audio_path VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (part_number, text_id)
);