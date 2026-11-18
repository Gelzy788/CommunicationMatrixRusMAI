package ru.matrix.matrix.children;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users_children")
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private Long childId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "child_name", length = 100)
    private String childName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public ChildEntity() {
    }

    public ChildEntity(Long parentId, String childName, LocalDate birthDate) {
        this.parentId = parentId;
        this.childName = childName;
        this.birthDate = birthDate;
    }

    public Long getChildId() {
        return childId;
    }

    public Long getParent() {
        return parentId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildEntity that)) return false;
        return childId != null && childId.equals(that.childId);
    }
}