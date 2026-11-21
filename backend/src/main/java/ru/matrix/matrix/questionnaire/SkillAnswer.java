package ru.matrix.matrix.questionnaire;

public enum SkillAnswer {
    NOT_USED("Не используется", 0),
    EMERGING("На стадии становления", 1),
    MASTERED("Усвоено", 2);
    private final String value;
    private final int point;

    SkillAnswer(String value, int point) {
        this.value = value;
        this.point = point;
    }

    public String getValue() {
        return value;
    }

    public int getPoints() {
        return point;
    }
}