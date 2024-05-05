-- USER
CREATE TABLE IF NOT EXISTS users
(
    id                BIGINT AUTO_INCREMENT,
    nickname          VARCHAR(20)   NULL,
    email             VARCHAR(100)  NULL,
    password          VARCHAR(255)  NULL,
    profile_image_url VARCHAR(1024) NULL,
    status_message    VARCHAR(255)  NULL,
    gender            VARCHAR(10)   NULL,
    birth_date        DATE          NULL,
    created_at        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_user_id PRIMARY KEY (id),
    CONSTRAINT uk_user_nickname UNIQUE (nickname),
    CONSTRAINT uk_user_email UNIQUE (email)
);
