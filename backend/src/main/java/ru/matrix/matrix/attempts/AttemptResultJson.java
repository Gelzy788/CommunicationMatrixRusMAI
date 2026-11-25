package ru.matrix.matrix.attempts;

import java.util.List;

public record AttemptResultJson(
        String questionnaireVersion,
        List<LevelJson> levels
) {

    public record LevelJson(String id, String entry, List<CellJson> cells) {}
    public record CellJson(String id, List<QuestionJson> questions, String comment) {}
    public record QuestionJson(String id, String answer) {}
}