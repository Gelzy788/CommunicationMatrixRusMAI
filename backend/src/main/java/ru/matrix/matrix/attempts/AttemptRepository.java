package ru.matrix.matrix.attempts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttemptRepository  extends JpaRepository<AttemptEntity, Long> {

    @Query("""
            SELECT attempt FROM AttemptEntity attempt
            JOIN ChildEntity child ON child.childId = attempt.childId
                       WHERE attempt.childId = :childId
                         AND child.parentId = :parentId
            
            """)
    List<AttemptEntity> findAllByChildIdAndUserId(@Param("child_id") Long childId,
                                                  @Param("parent_id") Long userId);
}
