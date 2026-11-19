package ru.matrix.matrix.questionnaire;

public enum SkillAnswer {
    NOT_USED("Не используется"),
    SURPASSED("Перерос"),
    MASTERED("Освоил");
    private final String value;

    SkillAnswer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}