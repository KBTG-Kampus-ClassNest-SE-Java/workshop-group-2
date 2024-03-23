CREATE TABLE IF NOT EXISTS cart (
    id SERIAL PRIMARY KEY,
    shopperId INT NOT NULL REFERENCES shopper(id),
    productId INT NOT NULL REFERENCES product(id),
    quantity INT NOT NULL
);