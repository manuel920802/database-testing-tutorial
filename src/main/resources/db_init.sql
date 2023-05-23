DROP ALL OBJECTS;
CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users(username, password) VALUES ('john','p@ssw0rd');
INSERT INTO users(username, password) VALUES ('jane','p@ssw0rd');
INSERT INTO users(username, password) VALUES ('admin','admin');