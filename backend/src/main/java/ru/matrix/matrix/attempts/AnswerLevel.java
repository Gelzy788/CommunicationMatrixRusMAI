package ru.matrix.matrix.attempts;

import java.util.List;

public class AnswerLevel {
    private final String id;
    private final List<AnswerCell>  cellList;

    public AnswerLevel(String id,
                       List<AnswerCell> cellList) {
        this.id = id;
        this.cellList = cellList;
    }

    public String getId() {
        return id;
    }

    public List<AnswerCell> getCellList() {
        return cellList;
    }
}
