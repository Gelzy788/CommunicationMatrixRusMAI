package ru.matrix.matrix.attempts.answer;

import ru.matrix.matrix.questionnaire.SkillAnswer;

public record AnswerQuestion(String id,
                             SkillAnswer answer) {
    public int points() {
        return answer.getPoints();
    }
}
