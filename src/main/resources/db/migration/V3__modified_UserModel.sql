ALTER TABLE user
    ADD is_email_verified BIT(1) NULL;

ALTER TABLE user
    MODIFY is_email_verified BIT (1) NOT NULL;

