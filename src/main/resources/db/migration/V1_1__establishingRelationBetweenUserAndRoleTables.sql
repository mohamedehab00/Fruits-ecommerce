CREATE TABLE user_role (
                           user_id BIGINT UNSIGNED NOT NULL,
                           role_id BIGINT UNSIGNED NOT NULL,
                           CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES user(id),
                           CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id) REFERENCES role(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;