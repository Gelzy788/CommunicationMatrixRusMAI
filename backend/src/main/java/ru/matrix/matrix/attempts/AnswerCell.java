package ru.matrix.matrix.attempts;

import java.util.List;

// Ответы на вопросы одной ячейки + комментарий родителя
public class AnswerCell {
    private final String id;
    private final List<AnswerQuestion> questionList;
    private final String comment;

    public AnswerCell(String id,
                      List<AnswerQuestion> questionList,
                      String comment) {
        this.id = id;
        this.questionList = List.copyOf(questionList);
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public List<AnswerQuestion> getQuestionList() {
        return questionList;
    }

    public String getComment() {
        return comment;
    }

    // Сколько максимум очков может быть в ячейке
    public int maxPoints() {
        return questionList.size() * 2;
    }
}
