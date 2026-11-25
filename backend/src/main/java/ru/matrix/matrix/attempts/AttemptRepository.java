package ru.matrix.matrix.attempts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.matrix.matrix.questionnaire.TestType;

import java.util.List;
import java.util.Optional;

public interface AttemptRepository extends JpaRepository<AttemptEntity, Long> {

    int countByChildIdAndTestType(Long childId, TestType testType);

    Optional<AttemptEntity> findFirstByChildIdAndTestTypeOrderByCompletedAtAsc(Long childId, TestType testType);

    List<AttemptEntity> findByChildIdAndTestTypeOrderByCompletedAtDesc(Long childId, TestType testType);

    // An attempt is found only if its child belongs to this parent
    @Query("""
            SELECT attempt FROM AttemptEntity attempt
            JOIN ChildEntity child ON child.childId = attempt.childId
            WHERE attempt.id = :attemptId
              AND child.parentId = :parentId
            """)
    Optional<AttemptEntity> findByIdAndParentId(@Param("attemptId") Long attemptId,
                                                @Param("parentId") Long parentId);
}
