-- Token expiration interval in seconds
INSERT INTO auth.configurations(config_key, config_value) VALUES ( 'token.expiration.interval', 600);

INSERT INTO auth.users(username, password) VALUES ( 'John', 'Smith' );
