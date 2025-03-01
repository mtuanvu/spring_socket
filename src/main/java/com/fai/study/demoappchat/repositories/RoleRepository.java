package com.fai.study.demoappchat.repositories;

import com.fai.study.demoappchat.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.id = :id")
    Optional<Role> findById(@Param("id") Long id);

    @Query("SELECT r FROM Role r")
    List<Role> findAll();
}
