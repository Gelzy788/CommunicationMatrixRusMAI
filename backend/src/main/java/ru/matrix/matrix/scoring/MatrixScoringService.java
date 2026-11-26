package ru.matrix.matrix.scoring;

import org.springframework.stereotype.Service;
import ru.matrix.matrix.attempts.answer.AnswerCell;
import ru.matrix.matrix.attempts.answer.AnswerLevel;
import ru.matrix.matrix.attempts.answer.AnswerQuestion;
import ru.matrix.matrix.attempts.answer.AttemptResult;
import ru.matrix.matrix.questionnaire.AnswerStage;
import ru.matrix.matrix.questionnaire.SkillAnswer;

import java.util.ArrayList;
import java.util.List;


@Service
public class MatrixScoringService {
    public CommunicationMatrixResult score(AttemptResult result) {
        List<LevelResult> levelList = new ArrayList<>();
        for (AnswerLevel level : result.levels()) {
            boolean outgrown = level.getAnswer() == AnswerStage.NO_OUTGROWN;
            List<CellResult> cellList = new ArrayList<>();

            for (AnswerCell cell : level.getCellList()) {
                List<QuestionResult> questionList = new ArrayList<>();
                for (AnswerQuestion question : cell.getQuestionList()) {
                    questionList.add(new QuestionResult(question.id(),
                            question.answer()));
                }

                CellStatus status = outgrown
                        ? CellStatus.SURPASSED
                        : cellPointsToStatus(scoreCellPoints(cell),
                        SkillAnswer.getMaxPoints() * questionList.size());

                cellList.add(new CellResult(cell.getId(), status, questionList));
            }

            levelList.add(new LevelResult(level.getId(), cellList));
        }
        return new CommunicationMatrixResult(
                result.questionnaireVersion(),
                levelList);
    }

    private int scoreCellPoints(AnswerCell cell) {
        int cellScore = 0;

        for (AnswerQuestion question : cell.getQuestionList()) {
            cellScore += question.getPoints();
        }
        return cellScore;
    }

    private CellStatus cellPointsToStatus(int cellPoints,
                                          int maxCellPoints) {
        if (cellPoints * 5 >= maxCellPoints * 4) return CellStatus.MASTERED; // >= 80%
        if (cellPoints * 5 >= maxCellPoints * 2) return CellStatus.EMERGING; // >= 40%
        return CellStatus.NOT_USED; // <40%
    }
}
