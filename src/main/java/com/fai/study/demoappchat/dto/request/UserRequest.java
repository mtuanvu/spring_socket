package com.fai.study.demoappchat.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
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


    public @NotBlank(message = "First name không được để trống") @Size(max = 50, message = "First name tối đa 50 ký tự") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name không được để trống") @Size(max = 50, message = "First name tối đa 50 ký tự") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last name không được để trống") @Size(max = 50, message = "Last name tối đa 50 ký tự") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name không được để trống") @Size(max = 50, message = "Last name tối đa 50 ký tự") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Giới tính không được để trống") @Pattern(regexp = "Male|Female|Other", message = "Giới tính phải là Male, Female hoặc Other") String getSex() {
        return sex;
    }

    public void setSex(@NotBlank(message = "Giới tính không được để trống") @Pattern(regexp = "Male|Female|Other", message = "Giới tính phải là Male, Female hoặc Other") String sex) {
        this.sex = sex;
    }

    public @Past(message = "Ngày sinh phải là ngày trong quá khứ") LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(@Past(message = "Ngày sinh phải là ngày trong quá khứ") LocalDate birthday) {
        this.birthday = birthday;
    }

    public @Email(message = "Email không hợp lệ") @NotBlank(message = "Email không được để trống") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email không hợp lệ") @NotBlank(message = "Email không được để trống") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Số điện thoại không được để trống") @Size(max = 20, message = "Số điện thoại tối đa 20 ký tự") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "Số điện thoại không được để trống") @Size(max = 20, message = "Số điện thoại tối đa 20 ký tự") String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

}
