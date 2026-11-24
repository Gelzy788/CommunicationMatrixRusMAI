package ru.matrix.matrix.attempts;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrix.matrix.attempts.answer.AttemptResult;
import ru.matrix.matrix.children.ChildRepository;
import ru.matrix.matrix.common.ApiException;
import ru.matrix.matrix.common.ErrorCode;
import ru.matrix.matrix.questionnaire.TestType;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AttemptsService {
    private static final int ATTEMPTS_LIMIT = 5;

    private final AttemptRepository attemptRepository;
    private final AttemptJsonMapper attemptJsonMapper;
    private final ChildRepository childRepository;
    private final Clock clock;


    public AttemptsService(AttemptRepository attemptRepository,
                           AttemptJsonMapper attemptJsonMapper,
                           ChildRepository childRepository,
                           Clock clock) {
        this.attemptRepository = attemptRepository;
        this.attemptJsonMapper = attemptJsonMapper;
        this.childRepository = childRepository;
        this.clock = clock;
    }

    @Transactional
    public void saveChildAttempt(Long childId, Long parentId,
                                 TestType testType, AttemptResult result) {
        if (testType == TestType.COMMUNICATION_MATRIX) {
            // Does the child belong to the user
            childRepository.findByChildIdAndParentId(childId, parentId)
                    .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND));

            // All of the child's previous responses
            List<AttemptEntity> allChildAttempts =
                    attemptRepository.findAllByChildIdAndUserId(childId, parentId);

            if (allChildAttempts.size() >= ATTEMPTS_LIMIT) {      // Delete the oldest attempt
                attemptRepository
                        .findFirstByChildIdOrderByCompletedAtAsc(childId)
                        .ifPresent(attemptRepository::delete);

            }
            AttemptEntity newAttempt = new AttemptEntity(childId, testType,
                    attemptJsonMapper.toJson(result),
                    OffsetDateTime.now(clock));
            attemptRepository.save(newAttempt);

        }
    }
}
