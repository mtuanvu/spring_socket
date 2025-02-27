package com.fai.study.demoappchat.repositories;

import com.fai.study.demoappchat.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByUserId(@Param("id") String id);

    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers();

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO users (uid, first_name, last_name, sex, birth_date, email, phone " +
//            "registration_date, account_id) " +
//            "VALUES :id, :firstName, :lastName, :sex, :dob, :email, :phone, :registrationDate, :accountId", nativeQuery = true)
//    void createUser(String id, String firstName, String lastName, String sex, LocalDate dob,
//                    String email, String phone, LocalDateTime registrationDate, String accountId);
//
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE users SET " +
//            "first_name = COALESCE(:firstName, first_name), last_name = COALESCE(:lastName, last_name), sex = COALESCE(:sex, sex), " +
//            "birth_date = COALESCE(:dob, birth_date), email = COALESCE(:email, email), phone = COALESCE(:phone, phone), registration_date = COALESCE(:registrationDate, registration_date), " +
//            " account_id = COALESCE(:accountId, account_id) " +
//            "WHERE uid = :id", nativeQuery = true)
//    void updateUser(String id, String firstName, String lastName, String sex, LocalDate dob,
//                    String email, String phone, LocalDateTime registrationDate, String accountId);
//
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM User u WHERE u.id = :id")
//    void deleteUser(String id);
}
