USE Letter;
ALTER TABLE users ADD UNIQUE (full_name);
CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       full_name VARCHAR(255) NOT NULL,
                       birth_date DATE NOT NULL
);

CREATE TABLE letters (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         sender_id INT,
                         receiver_id INT,
                         subject VARCHAR(255),
                         body TEXT,
                         send_date DATE,
                         FOREIGN KEY (sender_id) REFERENCES users(id),
                         FOREIGN KEY (receiver_id) REFERENCES users(id)
);