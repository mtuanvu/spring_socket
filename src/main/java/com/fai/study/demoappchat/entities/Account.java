package com.fai.study.demoappchat.entities;

import com.fai.study.demoappchat.utils.DateTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "phone", unique = true, nullable = false, length = 20)
    String phone;

    @Column(name = "password_hash", columnDefinition = "TEXT")
    String passwordHash;

    @Column(name = "email_verified")
    boolean emailVerified = false;

    @Column(name = "phone_verified")
    boolean phoneVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @OneToOne(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Token> tokens = new HashSet<>();

    public enum Status {
        ACTIVE, BLOCKED, DELETED
    }
}
