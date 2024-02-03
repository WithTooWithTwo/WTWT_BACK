-- USER
CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT AUTO_INCREMENT,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    nickname   VARCHAR(20)  NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    is_deleted TINYINT(1)   NOT NULL DEFAULT 0,
    CONSTRAINT pk_user_id PRIMARY KEY (id),
    CONSTRAINT uk_nickname UNIQUE (nickname),
    CONSTRAINT uk_user_email UNIQUE (email)
);
