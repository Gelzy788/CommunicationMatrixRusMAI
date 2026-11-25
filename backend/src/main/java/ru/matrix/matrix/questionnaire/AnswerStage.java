package ru.matrix.matrix.questionnaire;

import java.util.Arrays;
import java.util.Optional;

public enum AnswerStage {
    YES("yes", "Да"),
    NO_STOP("noStop", "Нет, остановка"),
    NO_OUTGROWN("noOutgrown", "Нет, перерос");

    private final String code;
    private final String value;

    AnswerStage(String code, String value) {
        this.code = code;
        this.value = value;
    }

    // Код ответа на входной вопрос уровня (L1_ENTRY и т.д.), который присылает фронт
    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static Optional<AnswerStage> fromCode(String code) {
        return Arrays.stream(values())
                .filter(stage -> stage.code.equals(code))
                .findFirst();
    }
}
