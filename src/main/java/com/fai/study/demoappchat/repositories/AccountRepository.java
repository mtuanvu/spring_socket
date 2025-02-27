package com.fai.study.demoappchat.repositories;

import com.fai.study.demoappchat.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByAccountId(@Param("id") String id);

    @Query(value = "SELECT a FROM Account a")
    List<Account> findAllAccount();

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO accounts (account_id, username, password_hash, email_verified, phone_verified, status) " +
//            "VALUES (:id, :username, :passwordHash, :emailVerified, :phoneVerified, :status)", nativeQuery = true)
//    void createAccount(String id, String username, String passwordHash, boolean emailVerified, boolean phoneVerified, String status);
//
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE accounts SET " +
//            "username = COALESCE(:username, username), password_hash = COALESCE(:passwordHash, password_hash), " +
//            "email_verified = COALESCE(:emailVerified, email_verified), phone_verified = COALESCE(:phoneVerified, phone_verified), status = COALESCE(:status, status) " +
//            "WHERE account_id = :id", nativeQuery = true)
//    void updateAccount(String id, String username, String passwordHash, boolean emailVerified, boolean phoneVerified, String status);
//
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM Account a WHERE a.id = :id")
//    void deleteAccount(String id);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM accounts " +
            "WHERE username = :username", nativeQuery = true)
    boolean existsByUsername(@org.springframework.data.repository.query.Param("username") String username);
}
