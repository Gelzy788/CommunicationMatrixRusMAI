package ru.matrix.matrix.questionnaire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class QuestionnaireService {
    private final CommunicationMatrix communicationMatrix;
    private final Map<String, Question> questionById;

    public QuestionnaireService(QuestionParser parser,
                                @Value("${questionnaire.questions_file}")
                                String fileName) throws IOException {
        this.communicationMatrix = parser.parse(fileName);

        HashMap<String, Question> questionById = new HashMap<>();

        for (Level level: communicationMatrix.getLevels()) {
            for (Cell cell : level.getCells()) {
                for (Question question : cell.getQuestions()) {
                    if (questionById.containsKey(question.getId())) {
                        throw new IllegalStateException("Question ID %s is repeated in the file"
                                .formatted(question.getId()));
                    }
                    questionById.put(question.getId(), question);
                }
            }
        }

        this.questionById = Map.copyOf(questionById);
    }

    public CommunicationMatrix getCommunicationMatrix() {
        return communicationMatrix;
    }

    public boolean questionExists(String questionId) {
        return questionById.containsKey(questionId);
    }
}
