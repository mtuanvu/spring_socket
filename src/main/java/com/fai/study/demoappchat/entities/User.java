package com.fai.study.demoappchat.entities;

import com.fai.study.demoappchat.utils.DateTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uid")
    String id;

    @Column(name = "first_name", length = 50)
    String firstName;

    @Column(name = "last_name", length = 50)
    String lastName;

    @Column(name = "sex", length = 10)
    String sex;

    @Column(name = "birth_date")
    LocalDate dob;

    @Column(name = "email", unique = true ,length = 100)
    String email;

    @Column(name = "phone", unique = true ,length = 20)
    String phone;

    @Column(name = "registration_date", updatable = false)
    LocalDateTime registrationDate = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", unique = true,
            foreignKey = @ForeignKey(name = "fk_user_account"))
    Account account;
}
