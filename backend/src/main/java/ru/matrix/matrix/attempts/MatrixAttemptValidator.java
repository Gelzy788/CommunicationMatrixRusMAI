import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ru.matrix.matrix.attempts.answer.AttemptResult;
import ru.matrix.matrix.attempts.answer.AnswerLevel;
import ru.matrix.matrix.attempts.answer.AnswerCell;
import ru.matrix.matrix.attempts.answer.AnswerQuestion;

import ru.matrix.matrix.common.ApiException;
import ru.matrix.matrix.common.ErrorCode;

import ru.matrix.matrix.questionnaire.definition.QuestionCommunicationMatrix;
import ru.matrix.matrix.questionnaire.definition.QuestionQuestionLevel;
import ru.matrix.matrix.questionnaire.definition.QuestionCell;
import ru.matrix.matrix.questionnaire.definition.Question;

import java.util.Set;
import java.util.HashSet;

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
        checkResult(result);
        checkQuestionnaireVersion(result);
        checkLevelCount(result);
        checkLevelIds(result);
        checkCellCount(result);
        checkCellIds(result);
        checkQuestionCount(result);
        checkQuestionIds(result);
        checkAnswers(result);
        checkComments(result);
    }

    //primary checks

    private void checkResult(AttemptResult result) {
        if (result == null) {
            throw new ApiException(ErrorCode.VALIDATION_ERROR);
        }
    }

    private void checkLevelCount(AttemptResult result) {
        if (result.levels().size() != questionCommunicationMatrix.getLevels().size()) {
            throw new ApiException(ErrorCode.VALIDATION_ERROR);
        }
    }

    private void checkQuestionnaireVersion(AttemptResult result) {
        if (!questionCommunicationMatrix.getVersion().equals(result.questionnaireVersion())) {
            throw new ApiException(ErrorCode.VALIDATION_ERROR);
        }
    }

    private void checkLevelIds(AttemptResult result) {
        Set<String> ids = new HashSet<>();

        for (AnswerLevel level : result.levels()) {
            getQuestionLevel(level.getId());

            if (!ids.add(level.getId())) {
                throw new ApiException(ErrorCode.VALIDATION_ERROR);
            }
        }
    }

    private void checkCellCount(AttemptResult result) {
        for (AnswerLevel level : result.levels()) {
            QuestionQuestionLevel questionLevel = getQuestionLevel(level.getId());

            if (level.getCellList() == null ||
                    level.getCellList().size() != questionLevel.getCells().size()) {
                throw new ApiException(ErrorCode.VALIDATION_ERROR);
            }
        }
    }

    private void checkCellIds(AttemptResult result) {
        for (AnswerLevel level : result.levels()) {
            QuestionQuestionLevel questionLevel = getQuestionLevel(level.getId());
            Set<String> ids = new HashSet<>();

            for (AnswerCell cell : level.getCellList()) {
                if (cell == null) {
                    throw new ApiException(ErrorCode.VALIDATION_ERROR);
                }

                getQuestionCell(questionLevel, cell.getId());

                if (!ids.add(cell.getId())) {
                    throw new ApiException(ErrorCode.VALIDATION_ERROR);
                }
            }
        }
    }

    private void checkQuestionCount(AttemptResult result) {
        for (AnswerLevel level : result.levels()) {
            QuestionQuestionLevel questionLevel = getQuestionLevel(level.getId());

            for (AnswerCell cell : level.getCellList()) {
                QuestionCell questionCell = getQuestionCell(questionLevel, cell.getId());

                if (cell.getQuestionList().size() != questionCell.getQuestions().size()) {
                    throw new ApiException(ErrorCode.VALIDATION_ERROR);
                }
            }
        }
    }

    private void checkQuestionIds(AttemptResult result) {
        for (AnswerLevel level : result.levels()) {
            QuestionQuestionLevel questionLevel = getQuestionLevel(level.getId());

            for (AnswerCell cell : level.getCellList()) {
                QuestionCell questionCell = getQuestionCell(questionLevel, cell.getId());
                Set<String> ids = new HashSet<>();

                for (AnswerQuestion question : cell.getQuestionList()) {
                    getQuestion(questionCell, question.id());

                    if (!ids.add(question.id())) {
                        throw new ApiException(ErrorCode.VALIDATION_ERROR);
                    }
                }
            }
        }
    }

    private void checkAnswers(AttemptResult result) {
        for (AnswerLevel level : result.levels()) {
            if (level.getAnswer() == null) {
                throw new ApiException(ErrorCode.VALIDATION_ERROR);
            }

            for (AnswerCell cell : level.getCellList()) {
                for (AnswerQuestion question : cell.getQuestionList()) {
                    if (question.answer() == null) {
                        throw new ApiException(ErrorCode.VALIDATION_ERROR);
                    }
                }
            }
        }
    }

    private void checkComments(AttemptResult result) {
        for (AnswerLevel level : result.levels()) {
            for (AnswerCell cell : level.getCellList()) {
                String comment = cell.getComment();

                if (comment != null && comment.length() > commentLimit) {
                    throw new ApiException(ErrorCode.VALIDATION_ERROR);
                }
            }
        }
    }

    //additional functions

    private QuestionQuestionLevel getQuestionLevel(String id) {
        for (QuestionQuestionLevel level : questionCommunicationMatrix.getLevels()) {
            if (level.getId().equals(id)) {
                return level;
            }
        }

        throw new ApiException(ErrorCode.VALIDATION_ERROR);
    }

    private QuestionCell getQuestionCell(QuestionQuestionLevel level, String id) {
        for (QuestionCell cell : level.getCells()) {
            if (cell.getId().equals(id)) {
                return cell;
            }
        }

        throw new ApiException(ErrorCode.VALIDATION_ERROR);
    }   

    private Question getQuestion(QuestionCell cell, String id) {
        for (Question question : cell.getQuestions()) {
            if (question.getId().equals(id)) {
                return question;
            }
        }

        throw new ApiException(ErrorCode.VALIDATION_ERROR);
    }

}