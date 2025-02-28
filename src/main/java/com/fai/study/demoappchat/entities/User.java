package com.fai.study.demoappchat.entities;

import com.fai.study.demoappchat.utils.DateTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "registration_date", updatable = false)
    LocalDateTime registrationDate = LocalDateTime.now();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", unique = true,
            foreignKey = @ForeignKey(name = "fk_user_account"))
    Account account;

    @ManyToMany(fetch = FetchType.LAZY)
            @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}))
    Set<Role> roles = new HashSet<>();
}
