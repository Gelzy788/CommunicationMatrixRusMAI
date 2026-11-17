package ru.matrix.matrix.children;

import java.time.LocalDate;

public record ChildResponse(Long id, String name, LocalDate birthDate) {
    public static ChildResponse toChildResponse(ChildEntity child) {
        return new ChildResponse(child.getChildId(), child.getChildName(), child.getBirthDate());
    }
}
