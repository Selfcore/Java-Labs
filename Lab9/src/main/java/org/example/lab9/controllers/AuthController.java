package org.example.lab9.controllers;

import org.example.lab9.controllers.dto.LoginRequest;
import org.example.lab9.controllers.dto.RegisterRequest;
import org.example.lab9.domain.Role;
import org.example.lab9.domain.User;
import org.example.lab9.repositories.RoleRepository;
import org.example.lab9.repositories.UserRepository;
import org.example.lab9.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          UserRepository userRepository, PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }


    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        Role role = roleRepository.findOneByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        user.setRoles(Set.of(role));

        userRepository.save(user);
    }
}
