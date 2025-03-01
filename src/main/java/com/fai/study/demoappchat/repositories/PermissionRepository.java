package com.fai.study.demoappchat.repositories;

import com.fai.study.demoappchat.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("SELECT p FROM Permission p WHERE p.id = :id")
    Optional<Permission> findById(@Param("id") Long id);

    @Query("SELECT p FROM Permission p")
    List<Permission> findAll();
}
