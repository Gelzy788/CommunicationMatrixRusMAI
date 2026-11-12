package ru.matrix.matrix.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "user_fullname", nullable = false, length = 100)
    private String userFullname;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "hash_password", nullable = false, columnDefinition = "TEXT")
    private String hashPassword;

    @Column(name = "user_agreement", nullable = false)
    private boolean userAgreement;

    protected UserEntity() {}

    public UserEntity(String userFullname, String email, String hashPassword, boolean userAgreement) {
        this.userFullname = userFullname;
        this.email = email;
        this.hashPassword = hashPassword;
        this.userAgreement = userAgreement;
    }

    public Long getParentId() { return parentId; }
    public String getUserFullname() { return userFullname; }
    public String getEmail() { return email; }
    public String getHashPassword() { return hashPassword; }
    public boolean isUserAgreement() { return userAgreement; }
}