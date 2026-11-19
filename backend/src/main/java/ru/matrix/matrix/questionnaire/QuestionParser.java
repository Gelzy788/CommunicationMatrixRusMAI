package ru.matrix.matrix.questionnaire;

import org.springframework.stereotype.Component;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectReader;

@Component
public class QuestionParser {

    private final ObjectReader reader;

    public QuestionParser(ObjectMapper objectMapper) {
        this.reader = objectMapper
                .readerFor(CommunicationMatrix.class)
                .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public CommunicationMatrix parse(byte[] json) {
        return reader.readValue(json);
    }
}