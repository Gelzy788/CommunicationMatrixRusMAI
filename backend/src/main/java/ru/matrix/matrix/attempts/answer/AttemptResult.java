package ru.matrix.matrix.attempts.answer;

import java.util.List;

public record AttemptResult(
        String questionnaireVersion,
        List<AnswerLevel> levels
) {
    public AttemptResult {
        levels = List.copyOf(levels);
    }
}
