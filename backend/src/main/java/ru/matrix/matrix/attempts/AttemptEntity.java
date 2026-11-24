package ru.matrix.matrix.attempts;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.matrix.matrix.questionnaire.TestType;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Table(name = "tests_results")
public class AttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    @JoinColumn(name = "child_id", nullable = false)
    private long childId;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type", nullable = false, length = 30)
    private TestType testType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "test_result", columnDefinition = "jsonb")
    private AttemptResultJson testResult;

    @Column(name = "test_completed_at")
    private OffsetDateTime completedAt;

    protected AttemptEntity() {
    }

    public AttemptEntity(long childId, TestType testType,
                         AttemptResultJson testResult, OffsetDateTime completedAt) {
        this.childId = childId;
        this.testType = testType;
        this.testResult = testResult;
        this.completedAt = completedAt;
    }

    public Long getId() { return id; }

    public long getChildId() { return childId; }

    public TestType getTestType() { return testType; }

    public Map<String, Object> getTestResult() { return testResult; }

    public OffsetDateTime getCompletedAt() { return completedAt; }
}