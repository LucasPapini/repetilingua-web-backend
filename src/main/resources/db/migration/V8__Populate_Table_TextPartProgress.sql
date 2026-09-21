INSERT INTO tb_text_part_progress (
    d1_read_listen,
    d1_listen_only,
    d1_final_check,
    d2_read_listen,
    d2_listen_only,
    d2_final_check,
    text_part_id,
    user_id,
    last_activity_at,
    complete
)
VALUES
(20, 20, 20, 20, 20, 20, 1, 1, CURRENT_TIMESTAMP, true),
(20, 20, 20, 20, 10, 0, 2, 1, CURRENT_TIMESTAMP, false),
(15, 0, 0, 0, 0, 0, 3, 1, CURRENT_TIMESTAMP, false);