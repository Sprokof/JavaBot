CREATE TABLE help_account (
  user_id BIGINT PRIMARY KEY,
  experience DOUBLE NOT NULL,
);

CREATE TABLE help_transaction (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  recipient BIGINT NOT NULL,
  created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  value DOUBLE NOT NULL,
  message VARCHAR(255)
);