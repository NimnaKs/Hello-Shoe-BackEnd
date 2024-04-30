package com.codeventlk.helloshoemanagementsystem.controller;

import com.codeventlk.helloshoemanagementsystem.secureAndResponse.response.JwtAuthResponse;
import com.codeventlk.helloshoemanagementsystem.secureAndResponse.secure.SignIn;
import com.codeventlk.helloshoemanagementsystem.secureAndResponse.secure.SignUp;
import com.codeventlk.helloshoemanagementsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class User {
    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthResponse> signup(@RequestBody SignUp signup){
        return ResponseEntity.ok(authenticationService.signUp(signup));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @GetMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refresh(
            @RequestParam ("refreshToken") String refreshToken
    ){
        System.out.println(refreshToken);
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
