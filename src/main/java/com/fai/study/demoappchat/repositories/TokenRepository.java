package com.fai.study.demoappchat.repositories;

import com.fai.study.demoappchat.entities.Account;
import com.fai.study.demoappchat.entities.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    @Query(value = "SELECT t FROM Token t WHERE t.refreshToken = :refreshToken")
    Optional<Token> findByRefreshToken(@Param("refreshToken") String refreshToken);

    @Modifying
    @Transactional
    @Query("UPDATE Token t SET t.revoked = true WHERE t.account= :account")
    void revokeAllByAccount(@Param("account") Account account);
}
