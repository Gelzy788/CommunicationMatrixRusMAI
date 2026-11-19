package ru.matrix.matrix.children;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.matrix.matrix.auth.AuthUser;

import java.util.List;

@RestController
@RequestMapping("/api/children")
public class ChildrenController {
    private final ChildrenService childrenService;

    public ChildrenController(ChildrenService childrenService) {
        this.childrenService = childrenService;
    }

    @GetMapping
    public List<ChildResponse> getChildren(@AuthenticationPrincipal AuthUser user) {
        return childrenService.getChildrenList(user.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addChild(@AuthenticationPrincipal AuthUser user,
                         @Valid @RequestBody ChildRequest request) {
        childrenService.addChild(user.getId(), request);
    }

    @GetMapping("/{childId}")
    public ChildResponse getChild(@AuthenticationPrincipal AuthUser user,
                                  @PathVariable Long childId) {
        return childrenService.getChild(user.getId(), childId);
    }

    @PutMapping("/{childId}")
    public void updateChild(@AuthenticationPrincipal AuthUser user,
                            @PathVariable Long childId,
                            @Valid @RequestBody ChildRequest request) {
        childrenService.updateChild(user.getId(), childId, request);
    }

    @DeleteMapping("/{childId}")
    public void deleteChild(@AuthenticationPrincipal AuthUser user,
                            @PathVariable Long childId) {
        childrenService.deleteChild(user.getId(), childId);
    }
}
