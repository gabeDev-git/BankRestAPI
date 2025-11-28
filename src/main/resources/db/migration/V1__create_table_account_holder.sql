CREATE TABLE account_holder (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(160) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    document VARCHAR(20) NOT NULL UNIQUE,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    birth_date DATE NOT NULL
);