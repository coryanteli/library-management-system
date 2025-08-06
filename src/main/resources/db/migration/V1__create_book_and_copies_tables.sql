CREATE TABLE book
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    author      VARCHAR(255) NOT NULL,
    genre       VARCHAR(100),
    year        INT,
    description TEXT
);

CREATE TABLE book_copy
(
    id        SERIAL PRIMARY KEY,
    book_id   INT NOT NULL,
    available BOOLEAN   DEFAULT TRUE,
    added_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
            REFERENCES book (id)
            ON DELETE CASCADE
);