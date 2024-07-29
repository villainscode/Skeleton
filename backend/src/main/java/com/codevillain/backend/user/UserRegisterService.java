package com.codevillain.backend.user;

import com.codevillain.backend.infrastructure.util.AESUtil;
import com.codevillain.backend.user.request.UserRegisterRequest;
import com.codevillain.backend.user.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRegisterService {

    @Value("${app.secret-key}")
    private String secretKey;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserRegisterService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(UserRegisterRequest userRegisterRequest) throws Exception {
        String decryptedPassword = AESUtil.decrypt(userRegisterRequest.getPassword(), secretKey);
        log.info("# decrypt password = {}", decryptedPassword);

        String hashedPassword = passwordEncoder.encode(decryptedPassword);
        log.info("# hashedPassword = {}", hashedPassword);
        // save


        return new UserResponse(userRegisterRequest.getEmail(), userRegisterRequest.getName(), userRegisterRequest.getPhone());
    }
}
