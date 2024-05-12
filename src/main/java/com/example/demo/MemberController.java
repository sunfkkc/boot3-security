package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public ResponseEntity<Response<Member>> getMembers(

            @RequestParam(name = "username",required = false) String username,
            @RequestParam(name = "email",required = false) String email

    ){

        List<Member> members = memberRepository.findByUsernameAndEmail(username, email);
        Response<Member> response = new Response<>(members);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public Member addMember(@RequestBody Member member){

        Member newMember = memberRepository.save(member);
        return newMember;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable String email) throws Exception{
        Member member = memberRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("존재하지 않는 이메일"));

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }


}
