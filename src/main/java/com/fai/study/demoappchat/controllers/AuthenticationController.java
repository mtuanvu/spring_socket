package com.fai.study.demoappchat.controllers;

import com.fai.study.demoappchat.dto.request.AuthenticationRequest;
import com.fai.study.demoappchat.dto.request.RefreshTokenRequest;
import com.fai.study.demoappchat.dto.response.AuthenticationResponse;
import com.fai.study.demoappchat.service.AuthenticationService;
import com.fai.study.demoappchat.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse result = authenticationService.authenticate(request);
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .data(result)
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .error("Authentication failed: " + e.getMessage())
                    .data(null)
                    .build();

            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request.getRefreshToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest request) {
        authenticationService.logout(request.getRefreshToken());
        return ResponseEntity.ok("Logged out successfully!");
    }
}
