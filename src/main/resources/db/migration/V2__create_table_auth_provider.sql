-- AUTH_PROVIDER
CREATE TABLE IF NOT EXISTS auth_provider
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id       BIGINT       NOT NULL,
    provider_type VARCHAR(50)  NOT NULL,
    provider_id   VARCHAR(100) NULL,
    refresh_token VARCHAR(300) NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
