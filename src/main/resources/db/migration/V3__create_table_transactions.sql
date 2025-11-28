CREATE TABLE IF NOT EXISTS transactions (
    id BIGSERIAL PRIMARY KEY,
    transactions_type VARCHAR(50) NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    sender_wallet_id BIGINT,
    receiver_wallet_id BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sender_wallet
        FOREIGN KEY (sender_wallet_id)
        REFERENCES wallet (id)
        ON DELETE SET NULL,

    CONSTRAINT fk_receiver_wallet
        FOREIGN KEY (receiver_wallet_id)
        REFERENCES wallet (id)
        ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS idx_transactions_sender_wallet
    ON transactions(sender_wallet_id);

CREATE INDEX IF NOT EXISTS idx_transactions_receiver_wallet
    ON transactions(receiver_wallet_id);

CREATE INDEX IF NOT EXISTS idx_transactions_created_at
    ON transactions(created_at);