package com.fai.study.demoappchat.service;

import com.fai.study.demoappchat.dto.request.AuthenticationRequest;
import com.fai.study.demoappchat.dto.response.AuthenticationResponse;
import com.fai.study.demoappchat.entities.Account;
import com.fai.study.demoappchat.entities.Token;
import com.fai.study.demoappchat.repositories.AccountRepository;
import com.fai.study.demoappchat.repositories.TokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.transaction.Transactional;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public AuthenticationServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @NonFinal
    @Value("${jwt.signer-key}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.signer-refresh}")
    protected String SIGNER_REFRESH;

    @Override
    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Account account = accountRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new RuntimeException("Username not found!")
        );

        boolean authenticated = passwordEncoder.matches(request.getPassword(), account.getPasswordHash());

        if (!authenticated) {
            throw new RuntimeException("Invalid password!");
        }

        tokenRepository.revokeAllByAccount(account);

        String accessToken = generateAccessToken(request.getUsername());
        String refreshToken  = generateRefreshToken(request.getUsername());

        Token token = new Token();
        token.setRefreshToken(refreshToken);
        token.setExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS));
        token.setAccount(account);
        token.setRevoked(false);
        tokenRepository.save(token);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(String refreshToken) {
        Token storedToken  = tokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new RuntimeException("Invalid refresh token!!")
        );

        if (storedToken.isRevoked()) {
            throw new RuntimeException("Refresh Token is revoked!");
        }

        if (storedToken.getExpiresAt().isBefore(Instant.now())) {
            throw new RuntimeException("Invalid refresh token!!!");
        }

        String newAccessToken = generateAccessToken(storedToken.getAccount().getUsername());

        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void logout(String refreshToken) {
        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        token.setRevoked(true);
        tokenRepository.save(token);
    }


    private String generateAccessToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(username)
                .issuer("chatapp.com")
                .issueTime(new Date()).expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("username", username)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRefreshToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("chatapp.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(7, ChronoUnit.DAYS).toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_REFRESH.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

}
