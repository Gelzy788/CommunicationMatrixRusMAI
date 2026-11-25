package ru.matrix.matrix.attempts;

import ru.matrix.matrix.questionnaire.TestType;

import java.time.OffsetDateTime;

// TODO: bring to the Attempt format from the API contract (cell statuses from scoring)
public record AttemptResponse(Long id, TestType testType,
                              OffsetDateTime completedAt, OffsetDateTime sentToCenterAt,
                              AttemptResultJson result) {
    public static AttemptResponse from(AttemptEntity attempt) {
        return new AttemptResponse(attempt.getId(), attempt.getTestType(),
                attempt.getCompletedAt(), attempt.getSentToCenterAt(),
                attempt.getTestResult());
    }
}
