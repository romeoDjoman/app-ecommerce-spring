-- SQL : Initialiser les users et les roles au démarrage de l'application

CREATE DATABASE IF NOT EXISTS inscic_pub_db;

USE inscic_pub_db;

INSERT INTO role (label) VALUES ('ADMIN'), ('AUTHOR'), ('REVIEWER') ON DUPLICATE KEY UPDATE label=label;

INSERT INTO users (name, email, pwd, active, role_id) VALUES
('admin', 'admin@gmail.com', SHA2('admin', 256), TRUE, (SELECT id FROM role WHERE label = 'ADMIN')),
('author', 'author@gmail.com', SHA2('author', 256), TRUE, (SELECT id FROM role WHERE label = 'AUTHOR')),
('reviewer', 'reviewer@gmail.com', SHA2('reviewer', 256), TRUE, (SELECT id FROM role WHERE label = 'REVIEWER'))
ON DUPLICATE KEY UPDATE name=VALUES(name), pwd=VALUES(pwd), active=VALUES(active), role_id=VALUES(role_id);


-- SPRING : Initialiser les users et les roles au démarrage de l'application
INSERT INTO role (label) VALUES
('ADMIN'),
('AUTHOR'),
('REVIEWER')
ON DUPLICATE KEY UPDATE label=label;

INSERT INTO users (name, email, pwd, active, role_id) VALUES
('admin', 'admin@gmail.com', SHA2('admin', 256), TRUE, (SELECT id FROM role WHERE label = 'ADMIN')),
('author', 'author@gmail.com', SHA2('author', 256), TRUE, (SELECT id FROM role WHERE label = 'AUTHOR')),
('reviewer', 'reviewer@gmail.com', SHA2('reviewer', 256), TRUE, (SELECT id FROM role WHERE label = 'REVIEWER'))
ON DUPLICATE KEY UPDATE name=VALUES(name), pwd=VALUES(pwd), active=VALUES(active), role_id=VALUES(role_id);
