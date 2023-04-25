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
                            name VARCHAR(100) UNIQUE,
                            PRIMARY KEY (id)
);


CREATE TABLE Posts (
                       id INT auto_increment,
                       title VARCHAR(100),
                       author VARCHAR(50),
                       content TEXT,
                       imgURL TEXT,
                       id_Categories INT,
                       FOREIGN KEY (id_Categories) REFERENCES Categories(id),
                       PRIMARY KEY (id)
);

INSERT INTO Users(username, email, password, isAdmin) VALUES ('admin', 'admin@admin.io', 'admin', true);
INSERT INTO Categories(name) VALUES ('Blog posts');
INSERT INTO Posts(title, author, content, id_Categories, imgURL) VALUES ('Test title', 'admin', 'this is a test content', 1, 'resources/img/img1.jpg');
INSERT INTO Posts(title, author, content, id_Categories, imgURL) VALUES ('Another Title', 'admin', 'Hello World!', 1, 'resources/img/img1.jpg');
INSERT INTO Posts(title, author, content, id_Categories, imgURL) VALUES ('A good title', 'admin', 'Welcome here', 1, 'resources/img/img1.jpg');
# SELECT id, username, email, password FROM users WHERE username='admin';