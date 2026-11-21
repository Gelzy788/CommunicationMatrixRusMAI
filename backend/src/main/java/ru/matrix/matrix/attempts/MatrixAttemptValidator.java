package ru.matrix.matrix.attempts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.matrix.matrix.common.ApiException;
import ru.matrix.matrix.common.ErrorCode;
import ru.matrix.matrix.questionnaire.definition.QuestionCommunicationMatrix;

@Component
public class MatrixAttemptValidator {
    private final QuestionCommunicationMatrix questionCommunicationMatrix;
    private final int commentLimit;

    public MatrixAttemptValidator(@Value("${attempts.commentLimit}") int commentLimit,
                                  QuestionCommunicationMatrix questionCommunicationMatrix) {
        this.commentLimit = commentLimit;
        this.questionCommunicationMatrix = questionCommunicationMatrix;
    }

    public void validate(AttemptResult result) {
        // Проверки
    }

    private void checkStructByModulesCount(AttemptResult result) {
        if (result.levels().size() != questionCommunicationMatrix.getLevels().size()) {
            throw new ApiException(ErrorCode.VALIDATION_ERROR);
        }
    }

}
