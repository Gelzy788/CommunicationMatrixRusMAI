-- Test type codes as in the API contract (UPPER_SNAKE_CASE, as the TestType enum stores them)
ALTER TABLE tests_results DROP CONSTRAINT tests_results_test_type_check;
ALTER TABLE tests_results ADD CONSTRAINT tests_results_test_type_check
    CHECK (test_type IN ('COMMUNICATION_MATRIX', 'SENSORY_PROFILE'));

-- When the mail server accepted the letter to the speech center; NULL until sent
ALTER TABLE tests_results ADD COLUMN sent_to_center_at TIMESTAMPTZ;
