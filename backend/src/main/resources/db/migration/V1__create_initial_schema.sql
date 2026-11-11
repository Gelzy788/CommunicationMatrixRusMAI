CREATE TABLE users(
	parent_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	user_fullname VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	hash_password TEXT NOT NULL,
	user_agreement BOOLEAN NOT NULL
);

CREATE TABLE users_children(
	child_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	parent_id BIGINT NOT NULL
		REFERENCES users(parent_id) ON DELETE CASCADE,
	child_name VARCHAR(100) NOT NULL,
	birth_date DATE NOT NULL
);

CREATE INDEX idx_children_parent_id
    ON users_children (parent_id);

CREATE TABLE tests_results(
	test_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	child_id BIGINT NOT NULL
		REFERENCES users_children(child_id) ON DELETE CASCADE,

	test_type VARCHAR(10) NOT NULL
		CHECK (test_type IN ('matrix', 'sensor')),
	
	test_result JSONB NOT NULL,

	test_completed_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_tests_child_date
    ON tests_results (child_id, test_completed_at DESC);
