package ru.matrix.matrix.questionnaire;

public enum AnswerStage {
    YES("Да"),
    NO_STOP("Нет, остановка"),
    NO_OUTGROWN("Нет, перерос");

    private final String value;

    AnswerStage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}