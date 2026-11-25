package ru.matrix.matrix.attempts;

import org.springframework.stereotype.Component;
import ru.matrix.matrix.attempts.answer.AnswerCell;
import ru.matrix.matrix.attempts.answer.AnswerLevel;
import ru.matrix.matrix.attempts.answer.AttemptResult;

@Component
public class AttemptJsonMapper {
    public AttemptResultJson toJson(AttemptResult r) {
        return new AttemptResultJson(r.questionnaireVersion(),
                r.levels().stream().map(this::levelToJson).toList());
    }
    private AttemptResultJson.LevelJson levelToJson(AnswerLevel l) {
        return new AttemptResultJson.LevelJson(l.getId(), l.getAnswer().getCode(),
                l.getCellList().stream().map(this::cellToJson).toList());
    }
    private AttemptResultJson.CellJson cellToJson(AnswerCell c) {
        return new AttemptResultJson.CellJson(c.getId(),
                c.getQuestionList().stream()
                        .map(q -> new AttemptResultJson.QuestionJson(q.id(), q.answer().getCode())).toList(),
                c.getComment());
    }
}
