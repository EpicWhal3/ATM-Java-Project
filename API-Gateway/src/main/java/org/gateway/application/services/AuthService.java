package org.gateway.application.services;

import org.gateway.infrastructure.entities.GatewayUser;
import org.gateway.infrastructure.entities.TokenBlacklist;
import org.gateway.infrastructure.repos.GatewayUserRepository;
import org.gateway.infrastructure.repos.TokenBlackListRepository;
import org.gateway.infrastructure.requestEntities.LoginRequest;
import org.gateway.infrastructure.DTO.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final GatewayUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtService;
    private final TokenBlackListRepository tokenBlacklistRepository;
    private final AuthenticationManager authenticationManager;


    public LoginDTO login(LoginRequest loginRequest) {
        GatewayUser user = userRepository.findByLogin(loginRequest.getLogin());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthorizationDeniedException("Invalid login or password");
        }

        String token = authenticateAndGenerateToken(loginRequest.getLogin(), loginRequest.getPassword());
        return new LoginDTO(token, user.getLogin(), user.getRole());
    }

    @Transactional
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!tokenBlacklistRepository.existsByToken(token)) {
            TokenBlacklist blacklistedToken = new TokenBlacklist();
            blacklistedToken.setToken(token);
            blacklistedToken.setBlackListedAt(Instant.now());
            tokenBlacklistRepository.save(blacklistedToken);
        }
    }

    public String authenticateAndGenerateToken(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
