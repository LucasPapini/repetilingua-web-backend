ALTER TABLE tb_text
    ADD CONSTRAINT fk_text_user FOREIGN KEY (user_id) REFERENCES tb_user(id);

ALTER TABLE tb_text_part
    ADD CONSTRAINT fk_text_part_text FOREIGN KEY (text_id) REFERENCES tb_text(id);

ALTER TABLE tb_text_part_progress
    ADD CONSTRAINT fk_progress_text_part FOREIGN KEY (text_part_id) REFERENCES tb_text_part(id);

ALTER TABLE tb_text_part_progress
    ADD CONSTRAINT fk_progress_user FOREIGN KEY (user_id) REFERENCES tb_user(id);

ALTER TABLE tb_user_role
    ADD CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES tb_role(id);

ALTER TABLE tb_user_role
    ADD CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES tb_user(id);