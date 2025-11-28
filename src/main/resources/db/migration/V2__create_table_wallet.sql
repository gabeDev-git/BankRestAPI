CREATE TABLE wallet (
    id BIGSERIAL PRIMARY KEY,

    balance NUMERIC(19, 2) DEFAULT 0 NOT NULL,

    holder_id BIGINT UNIQUE,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    transactions_count BIGINT DEFAULT 0,

    CONSTRAINT fk_wallet_holder
        FOREIGN KEY (holder_id)
        REFERENCES account_holder(id)
        ON DELETE CASCADE
);