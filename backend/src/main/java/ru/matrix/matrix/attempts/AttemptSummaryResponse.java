package ru.matrix.matrix.attempts;

import ru.matrix.matrix.questionnaire.TestType;

import java.time.OffsetDateTime;

public record AttemptSummaryResponse(Long id, TestType testType,
                                     OffsetDateTime completedAt, OffsetDateTime sentToCenterAt) {
    public static AttemptSummaryResponse from(AttemptEntity attempt) {
        return new AttemptSummaryResponse(attempt.getId(), attempt.getTestType(),
                attempt.getCompletedAt(), attempt.getSentToCenterAt());
    }
}
