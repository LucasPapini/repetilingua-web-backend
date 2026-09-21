-- USUÁRIOS
INSERT INTO TB_USER (NAME, EMAIL, password)  VALUES ('Admin User', 'admin@example.com', '$2a$10$w9y9yAyQ1j9TBKE19RpVZ.6V3k7h.cYl5xHibIe0ZL57e1tUZW.A6');
INSERT INTO TB_USER (NAME, EMAIL, password)  VALUES ('Maria', 'maria@example.com', '$2a$10$w9y9yAyQ1j9TBKE19RpVZ.6V3k7h.cYl5xHibIe0ZL57e1tUZW.A6');

-- TEXTOS
INSERT INTO TB_TEXT (COMPLETED,  MOMENT, USER_ID, MODULE, TITLE) VALUES (0,  CURRENT_TIMESTAMP, 1, 'Módulo 1', 'My Lord Bag of Rice');

-- PARTE DO TEXTO
INSERT INTO TB_TEXT_PART (PART_NUMBER, TEXT_ID, CONTENT, AUDIO_PATH) VALUES (1, 1, 'Long, long ago there lived in Japan a brave warrior known to all as Tawara Toda or “My Lord Bag of Rice”...', 'audios/full_intro.mp3');
INSERT INTO TB_TEXT_PART (PART_NUMBER, TEXT_ID, CONTENT, AUDIO_PATH) VALUES (2, 1, 'He had not gone far when he came to the bridge of Seta-no-Karashi crossing one end of the beautiful Lake Biwa...', 'audios/full_intro.mp3');
INSERT INTO TB_TEXT_PART (PART_NUMBER, TEXT_ID, CONTENT, AUDIO_PATH) VALUES (3, 1, 'He was a brave man, however, and putting aside all fear went forward dauntlessly. Crunch, crunch! He stepped now on the dragon’s body now between its coils, and without even one glance backward he went on his way...', 'audios/full_intro.mp3');

-- TEXTO PROGRESSO
-- Caso 1: Usuário COMPLETOU as 120 repetições (D1 e D2 finalizados)
INSERT INTO TB_TEXT_PART_PROGRESS (d1_read_listen, d1_listen_only, d1_final_check, d2_read_listen, d2_listen_only, d2_final_check, text_part_id, user_id, last_activity_at, complet) VALUES (20, 20, 20, 20, 20, 20, 1, 1, CURRENT_TIMESTAMP, TRUE);

-- Caso 2: Usuário finalizou o DIA 1 e está no meio do DIA 2 (Fase 2: Only Listen)
-- Total: 60 (D1) + 20 (D2 F1) + 10 (D2 F2) = 90/120 repetições
INSERT INTO TB_TEXT_PART_PROGRESS (d1_read_listen, d1_listen_only, d1_final_check, d2_read_listen, d2_listen_only, d2_final_check, text_part_id, user_id, last_activity_at, complet) VALUES (20, 20, 20, 20, 10, 0, 2, 1, CURRENT_TIMESTAMP, FALSE);

-- Caso 3: Usuário está no início do estudo (Fase 1 do DIA 1)
-- Total: 15/120 repetições
INSERT INTO TB_TEXT_PART_PROGRESS (d1_read_listen, d1_listen_only, d1_final_check, d2_read_listen, d2_listen_only, d2_final_check, text_part_id, user_id, last_activity_at, complet) VALUES (15, 0, 0, 0, 0, 0, 3, 1, CURRENT_TIMESTAMP, FALSE);

-- ROLES
-- Adicionando um seed para a entidade Role
INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

-- ADICIONANDO REGRAS (ROLE) PARA O USUARIO
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);