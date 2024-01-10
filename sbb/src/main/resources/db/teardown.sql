SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE users;
TRUNCATE TABLE boards;
TRUNCATE TABLE comments;
SET REFERENTIAL_INTEGRITY TRUE;

-- user table
INSERT INTO users (id, name, email, password, nickname, role, created_at) VALUES
    (1, 'test1', 'test1@example.com', '{bcrypt}$2a$10$8H0OT8wgtALJkig6fmypi.Y7jzI5Y7W9PGgRKqnVeS2cLWGifwHF2', 'test', 'USER', NOW());

-- board table
INSERT INTO boards (id, user_id, title, content, created_at) VALUES
    (1, 1, 'test title', 'test content', NOW()),
    (2, 1, 'test title2', 'test content2', NOW()),
    (3, 1, 'test title3', 'test content3', NOW()),
    (4, 1, 'test title4', 'test content', NOW()),
    (5, 1, 'test title5', 'test content2', NOW()),
    (6, 1, 'test title6', 'test content3', NOW()),
    (7, 1, 'test title7', 'test content', NOW()),
    (8, 1, 'test title8', 'test content2', NOW()),
    (9, 1, 'test title9', 'test content3', NOW()),
    (10, 1, 'test title10', 'test content', NOW()),
    (11, 1, 'test title11', 'test content2', NOW()),
    (12, 1, 'test title12', 'test content3', NOW()),
    (13, 1, 'test title13', 'test content', NOW()),
    (14, 1, 'test title14', 'test content2', NOW()),
    (15, 1, 'test title15', 'test content3', NOW()),
    (16, 1, 'test title16', 'test content', NOW()),
    (17, 1, 'test title17', 'test content2', NOW()),
    (18, 1, 'test title18', 'test content3', NOW()),
    (19, 1, 'test title19', 'test content', NOW()),
    (20, 1, 'test title20', 'test content2', NOW()),
    (21, 1, 'test title21', 'test content3', NOW());
