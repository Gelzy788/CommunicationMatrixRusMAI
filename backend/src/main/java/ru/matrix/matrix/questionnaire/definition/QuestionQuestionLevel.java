package ru.matrix.matrix.questionnaire.definition;

import java.util.List;

public class QuestionQuestionLevel {
    private String id;
    private String name;
    private String entryQuestionId;
    private List<QuestionCell> cells;

    public QuestionQuestionLevel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryQuestionId() {
        return entryQuestionId;
    }

    public void setEntryQuestionId(String entryQuestionId) {
        this.entryQuestionId = entryQuestionId;
    }

    public List<QuestionCell> getCells() {
        return cells;
    }

    public void setCells(List<QuestionCell> cells) {
        this.cells = cells;
    }
}

