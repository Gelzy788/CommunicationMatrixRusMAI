package ru.matrix.matrix.questionnaire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ru.matrix.matrix.questionnaire.definition.QuestionCommunicationMatrix;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ObjectReader;

import java.io.IOException;

@Configuration
public class QuestionnaireConfig {

    private final ObjectReader reader;

    public QuestionnaireConfig(ObjectMapper objectMapper) {
        this.reader = objectMapper
                .readerFor(QuestionCommunicationMatrix.class);
    }

    @Bean
    public QuestionCommunicationMatrix questionCommunicationMatrix(
            @Value("${questionnaire.questions_file}") Resource file) throws IOException {
        return reader.readValue(file.getContentAsByteArray());
    }
}