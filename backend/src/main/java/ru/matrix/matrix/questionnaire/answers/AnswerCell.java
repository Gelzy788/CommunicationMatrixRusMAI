package ru.matrix.matrix.questionnaire.answers;

import java.util.List;

// Ответы на вопросы одной ячейки
public class AnswerCell {
    private final String id;
    private final List<AnswerQuestion> questionList;

    public AnswerCell(String id,
                      List<AnswerQuestion> questionList) {
        this.id = id;
        this.questionList = questionList;
    }

    public String getId() {
        return id;
    }

    public List<AnswerQuestion> getQuestionList() {
        return questionList;
    }

    // Сколько максимум очков может быть в ячейке
    public int maxPoints() {
        return questionList.size() * 2;
    }
}
