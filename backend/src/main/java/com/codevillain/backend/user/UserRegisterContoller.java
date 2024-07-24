package com.codevillain.backend.user;

import com.codevillain.backend.user.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserRegisterContoller {

    @PostMapping("/register")
    public ResponseEntity<UserRequest> registerUser(@RequestBody UserRequest userRequest) {
        log.info("# userRequest = {}", userRequest.toString());

        return ResponseEntity.ok(userRequest);
    }
}
