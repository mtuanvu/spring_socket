package com.fai.study.demoappchat.entities;

import com.fai.study.demoappchat.utils.DateTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "accounts")
public class Account extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    String id;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password_hash", columnDefinition = "TEXT")
    String passwordHash;

    @Column(name = "email_verified")
    boolean emailVerified = false;

    @Column(name = "phone_verified")
    boolean phoneVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    User user;

    public enum Status {
        ACTIVE, BLOCKED, DELETED
    }

    public Account(String id) {
        this.id = id;
    }
}
