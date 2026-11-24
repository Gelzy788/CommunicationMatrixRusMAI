package ru.matrix.matrix.attempts.answer;

import ru.matrix.matrix.questionnaire.AnswerStage;

import java.util.List;

public class AnswerLevel {
    private final String id;
    private final AnswerStage answer;
    private final List<AnswerCell>  cellList;

    public AnswerLevel(String id, AnswerStage answer,
                       List<AnswerCell> cellList) {
        this.id = id;
        this.answer = answer;
        this.cellList = cellList;
    }

    public String getId() {
        return id;
    }

    public AnswerStage getAnswer() {
        return answer;
    }

    public List<AnswerCell> getCellList() {
        return cellList;
    }
}
