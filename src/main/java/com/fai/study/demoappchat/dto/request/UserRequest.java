package com.fai.study.demoappchat.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "First name không được để trống")
    @Size(max = 50, message = "First name tối đa 50 ký tự")
    private String firstName;

    @NotBlank(message = "Last name không được để trống")
    @Size(max = 50, message = "Last name tối đa 50 ký tự")
    private String lastName;

    @NotBlank(message = "Giới tính không được để trống")
    @Pattern(regexp = "Male|Female|Other", message = "Giới tính phải là Male, Female hoặc Other")
    private String sex;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate birthday;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 20, message = "Số điện thoại tối đa 20 ký tự")
    private String phone;

    private LocalDateTime registrationDate = LocalDateTime.now();

    @NotBlank(message = "accountId không được để trống")
    private String accountId;
}
