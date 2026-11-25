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
            checkChildOwner(childId, parentId);

            // The limit is per test type: delete the oldest attempt of this type
            if (attemptRepository.countByChildIdAndTestType(childId, testType) >= ATTEMPTS_LIMIT) {
                attemptRepository
                        .findFirstByChildIdAndTestTypeOrderByCompletedAtAsc(childId, testType)
                        .ifPresent(attemptRepository::delete);
            }
            AttemptEntity newAttempt = new AttemptEntity(childId, testType,
                    attemptJsonMapper.toJson(result),
                    OffsetDateTime.now(clock));
            attemptRepository.save(newAttempt);
        }
    }

    // Newest first: the frontend takes the first one as the latest attempt
    public List<AttemptSummaryResponse> getAttempts(Long childId, Long parentId, TestType testType) {
        checkChildOwner(childId, parentId);
        return attemptRepository.findByChildIdAndTestTypeOrderByCompletedAtDesc(childId, testType)
                .stream()
                .map(AttemptSummaryResponse::from)
                .toList();
    }

    public AttemptResponse getAttempt(Long attemptId, Long parentId) {
        return AttemptResponse.from(findAttempt(attemptId, parentId));
    }

    @Transactional
    public void deleteAttempt(Long attemptId, Long parentId) {
        attemptRepository.delete(findAttempt(attemptId, parentId));
    }

    // Someone else's attempt looks the same as a missing one: 404
    private AttemptEntity findAttempt(Long attemptId, Long parentId) {
        return attemptRepository.findByIdAndParentId(attemptId, parentId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND));
    }

    // Does the child belong to the user
    private void checkChildOwner(Long childId, Long parentId) {
        childRepository.findByChildIdAndParentId(childId, parentId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND));
    }
}
