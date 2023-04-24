DROP DATABASE TPPost;

CREATE DATABASE TPPost;
USE TPPost;

CREATE TABLE Users (
                       id INT auto_increment,
                       username VARCHAR(50),
                       email VARCHAR(255),
                       password VARCHAR(50),
                       isAdmin bool,
                       PRIMARY KEY (id)
);


CREATE TABLE Categories (
                            id INT auto_increment,
                            name VARCHAR(100),
                            PRIMARY KEY (id)
);


CREATE TABLE Posts (
                       id INT auto_increment,
                       title VARCHAR(100),
                       author VARCHAR(50),
                       content TEXT,
#                        createdAt VARCHAR(255),
                       imgURL TEXT,
                       id_Categories INT,
                       FOREIGN KEY (id_Categories) REFERENCES Categories(id),
                       PRIMARY KEY (id)
);

INSERT INTO Users(username, email, password, isAdmin) VALUES ('admin', 'admin@admin.io', 'admin', true);
INSERT INTO Posts(title, author, content, imgURL) VALUES ('Test title', 'admin', 'this is a test content', 'resources/img/img1.jpg');
INSERT INTO Posts(title, author, content, imgURL) VALUES ('Test title second', 'admin', 'Hello World!', 'resources/img/img1.jpg');
# SELECT id, username, email, password FROM users WHERE username='admin';