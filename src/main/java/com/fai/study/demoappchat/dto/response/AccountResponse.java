package com.fai.study.demoappchat.dto.response;

import com.fai.study.demoappchat.entities.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccountResponse {
    String id;
    String username;
    boolean emailVerified;
    boolean phoneVerified;
    Account.Status status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
