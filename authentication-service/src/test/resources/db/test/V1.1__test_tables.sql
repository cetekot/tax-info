CREATE TABLE auth.configurations
(
    config_key   VARCHAR NOT NULL,
    config_value VARCHAR NOT NULL,
    PRIMARY KEY (config_key)
);

CREATE TABLE auth.users
(
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE auth.tokens
(
    token    VARCHAR   NOT NULL,
    valid_to TIMESTAMP NOT NULL,
    PRIMARY KEY (token)
);
