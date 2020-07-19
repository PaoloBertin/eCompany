CREATE TABLE IF NOT EXISTS users(
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    enabled BOOLEAN NOT NULL,

    PRIMARY KEY(username)
);

CREATE TABLE authorities (
    username VARCHAR(256) NOT NULL,
    authority VARCHAR(256) NOT NULL,

    CONSTRAINT un_authoritues_01 UNIQUE (username,authority),
    CONSTRAINT fk_authorities_01 FOREIGN KEY(username) REFERENCES users(username)
);
