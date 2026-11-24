package ru.matrix.matrix.attempts;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.matrix.matrix.attempts.answer.AttemptResult;
import ru.matrix.matrix.auth.AuthUser;
import ru.matrix.matrix.questionnaire.TestType;

@RestController
@RequestMapping("/api")
public class AttemptsController {
    private final AttemptsService attemptsService;
    private final AttemptMapper attemptMapper;
    private final MatrixAttemptValidator matrixAttemptValidator;

    public AttemptsController(AttemptsService attemptsService,
                              AttemptMapper attemptMapper,
                              MatrixAttemptValidator matrixAttemptValidator) {
        this.attemptsService = attemptsService;
        this.attemptMapper = attemptMapper;
        this.matrixAttemptValidator = matrixAttemptValidator;
    }

    @PostMapping("/children/{childId}/attempts")
    public void saveChildAttempt(@PathVariable Long childId, @RequestParam TestType testType,
                                 @AuthenticationPrincipal AuthUser user,
                                 @RequestBody AttemptRequest request) {
        AttemptResult result = attemptMapper.toResult(request);
        matrixAttemptValidator.validate(result);
        attemptsService.saveChildAttempt(childId, user.getId(), testType, result);
    }
}
