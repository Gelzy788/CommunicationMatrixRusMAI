package ru.matrix.matrix.questionnaire;

import org.springframework.stereotype.Component;
import ru.matrix.matrix.questionnaire.definition.QuestionCommunicationMatrix;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectReader;

@Component
public class QuestionParser {

    private final ObjectReader reader;

    public QuestionParser(ObjectMapper objectMapper) {
        this.reader = objectMapper
                .readerFor(QuestionCommunicationMatrix.class)
                .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public QuestionCommunicationMatrix parse(byte[] json) {
        return reader.readValue(json);
    }
}