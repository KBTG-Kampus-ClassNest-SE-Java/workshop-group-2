CREATE TABLE IF NOT EXISTS kpoint (
    id SERIAL PRIMARY KEY,
    shopperId INT NOT NULL REFERENCES shopper(id),
    point INT NOT NULL
);