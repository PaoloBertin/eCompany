CREATE TABLE IF NOT EXISTS groups (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    group_name varchar(256) not NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS group_authorities (
    group_id BIGINT(20) NOT NULL,
    authority varchar(256) not null,

    CONSTRAINT fk_group_authorities_group FOREIGN KEY(group_id) REFERENCES groups(id)
);

CREATE TABLE IF NOT EXISTS group_members (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,
    username VARCHAR(256) NOT NULL,
    group_id BIGINT(20) NOT NULL,

    CONSTRAINT fk_group_members_group FOREIGN KEY(group_id) REFERENCES groups(id)
);

