CREATE SCHEMA IF NOT EXISTS request_processor;

CREATE TABLE IF NOT EXISTS request_processor.notification_outbox (
    id UUID PRIMARY KEY NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL,
    topic VARCHAR(255) NOT NULL,
    key VARCHAR(255) NOT NULL,
    value TEXT NOT NULL,
    sent BOOLEAN DEFAULT FALSE NOT NULL,
    attempt INTEGER DEFAULT 1 NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_outbox_sent_created_at
    ON request_processor.notification_outbox (sent, created_at);
