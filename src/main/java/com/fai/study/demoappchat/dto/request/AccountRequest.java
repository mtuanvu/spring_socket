package com.fai.study.demoappchat.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotBlank(message = "Username không được để trống")
    @Size(min = 3, max = 50, message = "Username phải có độ dài từ 3 đến 50 ký tự")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password phải có ít nhất 6 ký tự")
    private String password;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @NotBlank(message = "Status không được để trống")
    @Pattern(regexp = "ACTIVE|BLOCKED|DELETED", message = "Status phải là ACTIVE, BLOCKED hoặc DELETED")
    private String status;

    public @NotBlank(message = "Username không được để trống") @Size(min = 3, max = 50, message = "Username phải có độ dài từ 3 đến 50 ký tự") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username không được để trống") @Size(min = 3, max = 50, message = "Username phải có độ dài từ 3 đến 50 ký tự") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password không được để trống") @Size(min = 6, message = "Password phải có ít nhất 6 ký tự") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password không được để trống") @Size(min = 6, message = "Password phải có ít nhất 6 ký tự") String password) {
        this.password = password;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public @NotBlank(message = "Status không được để trống") @Pattern(regexp = "ACTIVE|BLOCKED|DELETED", message = "Status phải là ACTIVE, BLOCKED hoặc DELETED") String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank(message = "Status không được để trống") @Pattern(regexp = "ACTIVE|BLOCKED|DELETED", message = "Status phải là ACTIVE, BLOCKED hoặc DELETED") String status) {
        this.status = status;
    }
}
