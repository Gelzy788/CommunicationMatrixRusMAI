package ru.matrix.matrix.questionnaire;

import java.util.Arrays;
import java.util.Optional;

public enum SkillAnswer {
    NOT_USED("notUsed", "Не используется", 0),
    EMERGING("emerging", "На стадии становления", 1),
    MASTERED("mastered", "Усвоено", 2);

    private static final int MAX_POINTS = Arrays.stream(values())
            .mapToInt(SkillAnswer::getPoints)
            .max()
            .orElse(0);

    private final String code;
    private final String value;
    private final int point;

    SkillAnswer(String code, String value, int point) {
        this.code = code;
        this.value = value;
        this.point = point;
    }

    // Код, который присылает фронт: "notUsed", "emerging", "mastered"
    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public int getPoints() {
        return point;
    }

    public static Optional<SkillAnswer> fromCode(String code) {
        return Arrays.stream(values())
                .filter(answer -> answer.code.equals(code))
                .findFirst();
    }

    public static int getMaxPoints() {
        return MAX_POINTS;
    }
}
