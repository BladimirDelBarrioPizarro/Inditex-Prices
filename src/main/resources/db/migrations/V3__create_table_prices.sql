CREATE TABLE prices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    price_list VARCHAR(255),
    product_id INT,
    priority INT,
    price DECIMAL,
    curr VARCHAR(3),
    CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brands(id)
);
