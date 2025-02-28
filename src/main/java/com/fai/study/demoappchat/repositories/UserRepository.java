package com.fai.study.demoappchat.repositories;

import com.fai.study.demoappchat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByUserId(@Param("id") String id);

    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers();

}
