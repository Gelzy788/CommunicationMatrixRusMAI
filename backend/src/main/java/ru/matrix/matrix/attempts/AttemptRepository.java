package ru.matrix.matrix.attempts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttemptRepository  extends JpaRepository<AttemptEntity, Long> {

    @Query("""
            SELECT attempt FROM AttemptEntity attempt
            JOIN ChildEntity child ON child.childId = attempt.childId
                       WHERE attempt.childId = :childId
                         AND child.parentId = :parentId
            
            """)
    List<AttemptEntity> findAllByChildIdAndUserId(@Param("childId") Long childId,
                                                  @Param("parentId") Long userId);

    Optional<AttemptEntity> findFirstByChildIdOrderByCompletedAtAsc(Long childId);

    List<AttemptEntity> childId(long childId);
}
