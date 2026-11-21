package ru.matrix.matrix.attempts;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.matrix.matrix.auth.AuthUser;
import ru.matrix.matrix.questionnaire.TestType;

@RestController
@RequestMapping("/api")
public class AttemptsController {
    private final AttemptsService attemptsService;

    public AttemptsController(AttemptsService attemptsService) {
        this.attemptsService = attemptsService;
    }

    @PostMapping("/children/{childId}/attempts")
    public void saveChildAttempt(@PathVariable Long childId, @RequestParam TestType testType,
                                 @AuthenticationPrincipal AuthUser user) {
        // TODO: сделать конвертацию RestBody в ООП
    }
}
