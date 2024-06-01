-- CATEGORY
create TABLE IF NOT EXISTS category
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    parent_id  BIGINT      NULL,
    name       VARCHAR(50) NOT NULL,
    level      VARCHAR(20) NOT NULL,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES category (id)
);
