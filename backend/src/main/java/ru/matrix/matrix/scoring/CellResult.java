package ru.matrix.matrix.scoring;

import java.util.List;

public record CellResult(String id, CellStatus status, List<QuestionResult> questions) {
    public CellResult {
        questions = List.copyOf(questions);
    }
}
