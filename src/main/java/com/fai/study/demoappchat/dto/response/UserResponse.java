package com.fai.study.demoappchat.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String sex;
    LocalDate birthDate;
    String email;
    String phone;
    LocalDateTime registrationDate;
    AccountResponse account;
}
