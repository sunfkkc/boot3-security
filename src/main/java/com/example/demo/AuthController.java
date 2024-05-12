package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Member> register(@RequestBody Member member){
        Member newMember = authenticationService.signup(member);
        return ResponseEntity.ok(newMember);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody Member member){
        Member authMember = authenticationService.authenticate(member);
        String jwtToken = jwtService.generateToken(authMember);

        return ResponseEntity.ok(jwtToken);
    }
}
