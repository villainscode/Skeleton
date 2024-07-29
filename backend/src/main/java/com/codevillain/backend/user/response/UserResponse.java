package com.codevillain.backend.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String createdAt;
    private String updatedAt;

    // 간단한 생성자 (ID 없이)
    public UserResponse(String email, String name, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}