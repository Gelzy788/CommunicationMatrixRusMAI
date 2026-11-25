package ru.matrix.matrix.attempts;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.matrix.matrix.attempts.answer.AttemptResult;
import ru.matrix.matrix.auth.AuthUser;
import ru.matrix.matrix.questionnaire.TestType;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void saveChildAttempt(@PathVariable Long childId, @RequestParam TestType testType,
                                 @AuthenticationPrincipal AuthUser user,
                                 @RequestBody AttemptRequest request) {
        AttemptResult result = attemptMapper.toResult(request);
        matrixAttemptValidator.validate(result);
        attemptsService.saveChildAttempt(childId, user.getId(), testType, result);
    }

    @GetMapping("/children/{childId}/attempts")
    public List<AttemptSummaryResponse> getAttempts(@PathVariable Long childId,
                                                    @RequestParam TestType testType,
                                                    @AuthenticationPrincipal AuthUser user) {
        return attemptsService.getAttempts(childId, user.getId(), testType);
    }

    @GetMapping("/attempts/{attemptId}")
    public AttemptResponse getAttempt(@PathVariable Long attemptId,
                                      @AuthenticationPrincipal AuthUser user) {
        return attemptsService.getAttempt(attemptId, user.getId());
    }

    @DeleteMapping("/attempts/{attemptId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttempt(@PathVariable Long attemptId,
                              @AuthenticationPrincipal AuthUser user) {
        attemptsService.deleteAttempt(attemptId, user.getId());
    }
}
