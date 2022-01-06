package com.study.springsecurity.security.controller;

import com.study.springsecurity.rest.model.entity.Member;
import com.study.springsecurity.security.model.LoginRequestDto;
import com.study.springsecurity.security.model.LoginResponseDto;
import com.study.springsecurity.security.model.SignupRequestDto;
import com.study.springsecurity.rest.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
public class SignController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request){
        try{
            LoginResponseDto response = memberService.login(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NoSuchElementException exception){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody SignupRequestDto request){
        Member member = memberService.singUp(request);
        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }
}