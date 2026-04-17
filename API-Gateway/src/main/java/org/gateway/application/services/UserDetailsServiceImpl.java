package org.gateway.application.services;

import org.gateway.infrastructure.entities.GatewayUser;
import org.gateway.infrastructure.repos.GatewayUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final GatewayUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        GatewayUser user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }

        return new User(user.getLogin(), user.getPassword(),
                user.getRole().stream().map(role -> new SimpleGrantedAuthority(role.name())).toList());
    }
}
