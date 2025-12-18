package org.example.lab9.controllers;

import org.example.lab9.controllers.dto.RegisterRequest;
import org.example.lab9.domain.Role;
import org.example.lab9.domain.User;
import org.example.lab9.repositories.RoleRepository;
import org.example.lab9.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class AuthPageController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthPageController(UserRepository userRepository,
                              RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new RegisterRequest("", ""));
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") RegisterRequest request) {

        if (userRepository.findByUsername(request.username()) != null) {
            return "redirect:/register?error";
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        Role role = roleRepository.findOneByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        user.setRoles(Set.of(role));
        userRepository.save(user);

        return "redirect:/login?registered";
    }
}
