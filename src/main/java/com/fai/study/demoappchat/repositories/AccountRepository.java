package com.fai.study.demoappchat.repositories;

import com.fai.study.demoappchat.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM accounts " +
            "WHERE username = :username", nativeQuery = true)
    boolean existsByUsername(@org.springframework.data.repository.query.Param("username") String username);
}
