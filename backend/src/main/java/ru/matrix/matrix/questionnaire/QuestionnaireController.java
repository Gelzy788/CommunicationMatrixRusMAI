package ru.matrix.matrix.questionnaire;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {
    private final MatrixQuestionnaireService matrixQuestionnaireService;

    public QuestionnaireController(MatrixQuestionnaireService matrixQuestionnaireService) {
        this.matrixQuestionnaireService = matrixQuestionnaireService;
    }

    @GetMapping("/{testType}")
    public ResponseEntity<byte[]> getQuestionJson(@PathVariable TestType testType) {
        QuestionnaireFile file = switch (testType) {
            case COMMUNICATION_MATRIX -> matrixQuestionnaireService.getFile();
        };

        return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .contentType(MediaType.APPLICATION_JSON)
                .eTag(file.etag())
                .body(file.questionsJson());
    }
}
