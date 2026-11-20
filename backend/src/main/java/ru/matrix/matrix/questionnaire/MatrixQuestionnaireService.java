package ru.matrix.matrix.questionnaire;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.matrix.matrix.questionnaire.definition.QuestionCell;
import ru.matrix.matrix.questionnaire.definition.QuestionCommunicationMatrix;
import ru.matrix.matrix.questionnaire.definition.QuestionQuestionLevel;
import ru.matrix.matrix.questionnaire.definition.Question;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

@Service
public class MatrixQuestionnaireService {
    private final QuestionCommunicationMatrix communicationMatrix;
    private final Map<String, Question> questionById;
    private final byte[] questionsJson;
    private final String etag;

    public MatrixQuestionnaireService(QuestionParser parser,
                                      @Value("${questionnaire.questions_file}")
                                Resource file) throws IOException {
        this.questionsJson = file.getContentAsByteArray();
        this.communicationMatrix = parser.parse(questionsJson);
        this.questionById = Map.copyOf(indexQuestions(communicationMatrix));
        this.etag = computeEtag(questionsJson);
    }

    private static String computeEtag(byte[] questionsJson) {
        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(hasher.digest(questionsJson), 0, 8);
        } catch (NoSuchAlgorithmException err) {
            throw new IllegalStateException("Алгоритм SHA-256 недоступен", err);
        }
    }

    private static Map<String, Question> indexQuestions(QuestionCommunicationMatrix communicationMatrix) {
        HashMap<String, Question> questionById = new HashMap<>();

        for (QuestionQuestionLevel level: communicationMatrix.getLevels()) {
            for (QuestionCell cell : level.getCells()) {
                for (Question question : cell.getQuestions()) {
                    if (questionById.containsKey(question.getId())) {
                        throw new IllegalStateException("Question ID %s is repeated in the file"
                                .formatted(question.getId()));
                    }
                    questionById.put(question.getId(), question);
                }
            }
        }
        return questionById;
    }

    public boolean questionExists(String questionId) {
        return questionById.containsKey(questionId);
    }

    public QuestionCommunicationMatrix getCommunicationMatrix() {
        return communicationMatrix;
    }

    public QuestionnaireFile getFile() {
        return new QuestionnaireFile(questionsJson, etag);
    }
}
