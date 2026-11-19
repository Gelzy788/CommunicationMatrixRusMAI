package ru.matrix.matrix.questionnaire;

import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Component
public class QuestionParser {

    private final ObjectMapper objectMapper;

    public QuestionParser() {
        this.objectMapper = new ObjectMapper();
    }

    public CommunicationMatrix parse(String fileName) throws IOException {

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException(
                    "File not found: " + fileName
            );
        }

        try (inputStream) {
            return objectMapper.readValue(inputStream, CommunicationMatrix.class);
        }
    }
}
