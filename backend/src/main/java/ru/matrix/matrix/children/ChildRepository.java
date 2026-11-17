package ru.matrix.matrix.children;

import ru.matrix.matrix.children.ChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<ChildEntity, Long> {

    List<ChildEntity> findByParentIdOrderByChildIdAsc(Long parentId);

    Optional<ChildEntity> findByChildIdAndParentId(Long childId, Long parentId);
}