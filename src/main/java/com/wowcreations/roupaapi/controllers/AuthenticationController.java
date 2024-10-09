package com.wowcreations.roupaapi.controllers;

import com.wowcreations.roupaapi.config.TokenService;
import com.wowcreations.roupaapi.controllers.dto.AuthenticationDTO;
import com.wowcreations.roupaapi.controllers.dto.RegisterDTO;
import com.wowcreations.roupaapi.entities.CustomUser;
import com.wowcreations.roupaapi.entities.LoginResponseDTO;
import com.wowcreations.roupaapi.exceptions.NameLengthException;
import com.wowcreations.roupaapi.exceptions.PasswordLengthException;
import com.wowcreations.roupaapi.exceptions.UsernameExistsException;
import com.wowcreations.roupaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO request) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = this.tokenService.generateToken((CustomUser) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO request) {
        String name = request.name();
        String username = request.username();
        String email = request.email();
        String password = request.password();

        if (name.length() < 3)
            throw new NameLengthException();

        if (this.userRepository.findByUsername(username) != null)
            throw new UsernameExistsException();

        if (password.length() < 8)
            throw new PasswordLengthException();

        String hash = new BCryptPasswordEncoder().encode(request.password());
        CustomUser user = new CustomUser(email, name, username, hash);

        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}