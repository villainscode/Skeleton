package com.codevillain.backend.user;

import com.codevillain.backend.common.ApiResponse;
import com.codevillain.backend.exceptions.DecryptionException;
import com.codevillain.backend.user.request.UserRegisterRequest;
import com.codevillain.backend.user.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserRegisterContoller {

    private final UserRegisterService userRegisterService;

    public UserRegisterContoller(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        log.info("# userRequest = {}", userRegisterRequest.toString());
        try {
            UserResponse registeredUser = userRegisterService.registerUser(userRegisterRequest);
            return ResponseEntity.ok(ApiResponse.success(registeredUser));
        } catch (DecryptionException e) {
            log.error("Failed to decrypt password", e);
            return ResponseEntity.badRequest().body(ApiResponse.error("Invalid password format"));
        } catch (Exception e) {
            log.error("Unexpected error during user registration", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("An unexpected error occurred"));
        }
    }
}
