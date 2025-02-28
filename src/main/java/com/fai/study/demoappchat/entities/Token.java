package com.fai.study.demoappchat.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "token_id")
    String id;

    @Column(name = "refresh_token", columnDefinition = "TEXT", nullable = false)
    String refreshToken;

    @Column(name = "expires_at", nullable = false)
    Instant expiresAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    Instant createdAt = Instant.now();

    @Column(name = "revoked", nullable = false)
    boolean revoked = false;

    @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_token_account"))
    Account account;
}
