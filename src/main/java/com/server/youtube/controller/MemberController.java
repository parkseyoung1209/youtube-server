package com.server.youtube.controller;

import com.server.youtube.config.TokenProvider;
import com.server.youtube.domain.Member;
import com.server.youtube.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody Member vo) {
        memberService.signup(vo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Member vo) {
        Member member = memberService.login(vo.getId(), vo.getPassword());
        if(member != null) {
            String token = tokenProvider.create(member);
            return ResponseEntity.ok(token);
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
