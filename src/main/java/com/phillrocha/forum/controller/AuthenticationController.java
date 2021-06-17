package com.phillrocha.forum.controller;

import com.phillrocha.forum.config.security.TokenService;
import com.phillrocha.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginData = form.convert();

        try {
            Authentication authentication = authManager.authenticate(loginData);

            String token = tokenService.generateToken(authentication);
            System.out.println(token);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
