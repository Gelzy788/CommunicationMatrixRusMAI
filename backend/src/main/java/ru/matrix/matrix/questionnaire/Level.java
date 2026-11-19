package ru.matrix.matrix.questionnaire;

import java.util.List;

public class Level {
    private String id;
    private String name;
    private String entryQuestionId;
    private List<Cell> cells;

    public Level() {
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

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}

