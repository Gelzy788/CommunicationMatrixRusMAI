package ru.matrix.matrix.children;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrix.matrix.auth.AuthUser;
import ru.matrix.matrix.common.ApiException;
import ru.matrix.matrix.common.ErrorCode;

import java.util.List;

@Service
public class ChildrenService {
    private final ChildRepository childRepository;

    public ChildrenService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<ChildResponse> getChildrenList(Long parentId) {
        return childRepository.findByParentIdOrderByChildIdAsc(parentId)
                .stream()
                .map(ChildResponse::toChildResponse)
                .toList();
    }

    @Transactional
    public void addChild(Long userId, ChildRequest request) {
        ChildEntity newChild = new ChildEntity(userId,
                request.name().trim(),
                request.birthDate());
        childRepository.save(newChild);
    }

    public ChildResponse getChild(Long parentId, Long childId) {
        return ChildResponse.toChildResponse(findChildById(parentId, childId));
    }

    @Transactional
    public void updateChild(Long parentId, Long childId, ChildRequest request) {
        ChildEntity child = findChildById(parentId, childId);
        child.setChildName(request.name());
        child.setBirthDate(request.birthDate());
    }

    @Transactional
    public void deleteChild(Long parentId, Long childId) {
        childRepository.delete(findChildById(parentId, childId));
    }

    private ChildEntity findChildById(Long parentId, Long childId) {
        return childRepository.findByChildIdAndParentId(childId, parentId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND));
    }
}
