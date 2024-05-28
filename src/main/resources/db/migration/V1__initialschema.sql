CREATE TABLE `role`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime NULL,
    modified_at datetime NULL,
    name        VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    modified_at     datetime NULL,
    name            VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    hashed_password VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);