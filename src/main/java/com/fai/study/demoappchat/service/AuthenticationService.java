package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.AuthenticationRequest;
import com.fai.study.demoappchat.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse refreshToken(String refreshToken);
    void logout(String refreshToken);
}
