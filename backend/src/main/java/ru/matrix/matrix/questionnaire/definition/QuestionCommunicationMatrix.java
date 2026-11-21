package ru.matrix.matrix.questionnaire.definition;

import java.util.List;

public class QuestionCommunicationMatrix {
    private String version;
    private List<QuestionQuestionLevel> levels;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<QuestionQuestionLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<QuestionQuestionLevel> levels) {
        this.levels = levels;
    }
}