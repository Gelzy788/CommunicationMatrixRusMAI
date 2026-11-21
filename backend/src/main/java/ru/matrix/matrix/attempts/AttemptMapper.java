package ru.matrix.matrix.attempts;

import org.springframework.stereotype.Component;
import ru.matrix.matrix.attempts.AttemptRequest;
import ru.matrix.matrix.attempts.AttemptRequest.LevelRequest;
import ru.matrix.matrix.common.ApiException;
import ru.matrix.matrix.common.ErrorCode;
import ru.matrix.matrix.questionnaire.AnswerStage;
import ru.matrix.matrix.questionnaire.SkillAnswer;
import ru.matrix.matrix.questionnaire.definition.QuestionCommunicationMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class AttemptMapper {
    private static final String ENTRY_SUFFIX = "_ENTRY";
    private static final String COMMENT_KEY = "comment";

    private final QuestionCommunicationMatrix questionnaire;

    public AttemptMapper(QuestionCommunicationMatrix questionnaire) {
        this.questionnaire = questionnaire;
    }

    public AttemptResult toResult(AttemptRequest request) {
        String version = request.questionnaireVersion() != null
                ? request.questionnaireVersion()
                : questionnaire.getVersion();

        List<AnswerLevel> levels = request.levels().stream()
                .map(this::toLevel)
                .toList();
        return new AttemptResult(version, levels);
    }

    private AnswerLevel toLevel(LevelRequest level) {
        String entryKey = level.getId() + ENTRY_SUFFIX;
        Map<String, String> other = level.getOtherFields();

        // Кроме "<id>_ENTRY" у уровня не должно быть неизвестных полей
        if (other.size() != 1 || !other.containsKey(entryKey)) {
            throw validationError();
        }
        AnswerStage entryAnswer = AnswerStage.fromCode(other.get(entryKey))
                .orElseThrow(AttemptMapper::validationError);

        List<AnswerCell> cells = level.getCells().entrySet().stream()
                .map(cell -> toCell(cell.getKey(), cell.getValue()))
                .toList();

        return new AnswerLevel(level.getId(), entryAnswer, cells);
    }

    private AnswerCell toCell(String cellId, Map<String, String> fields) {
        if (fields == null) {
            throw validationError();
        }

        String comment = "";
        List<AnswerQuestion> questions = new ArrayList<>();

        for (Map.Entry<String, String> field : fields.entrySet()) {
            if (COMMENT_KEY.equals(field.getKey())) {
                comment = field.getValue() == null ? "" : field.getValue();
                continue;
            }
            SkillAnswer answer = SkillAnswer.fromCode(field.getValue())
                    .orElseThrow(AttemptMapper::validationError);
            questions.add(new AnswerQuestion(field.getKey(), answer));
        }

        return new AnswerCell(cellId, questions, comment);
    }

    private static ApiException validationError() {
        return new ApiException(ErrorCode.VALIDATION_ERROR);
    }
}
