package com.example.demo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Member signup(Member input){
        Member member = new Member();
        member.setEmail(input.getEmail());
        member.setPassword(passwordEncoder.encode(input.getPassword()));
        member.setUsername(input.getUsername());
        return memberRepository.save(member);
    }

    public Member authenticate(Member input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return memberRepository.findByEmail(input.getEmail()).orElseThrow();

    }

}
