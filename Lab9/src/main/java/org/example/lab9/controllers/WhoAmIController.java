package org.example.lab9.controllers;

import org.example.lab9.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WhoAmIController {
    @GetMapping("/whoami")
    public UserInfo whoAmI() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return new UserInfo("Anonymous", List.of());
        }

        Object principal = auth.getPrincipal();
        String username = "Unknown";
        List<String> roles;

        if (principal instanceof CustomUserDetails userDetails) {
            username = userDetails.getUsername();
            roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        } else {
            roles = List.of();
        }

        return new UserInfo(username, roles);
    }

    public record UserInfo(String username, List<String> roles) {}
}
