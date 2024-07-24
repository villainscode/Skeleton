package com.codevillain.backend.user.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {
    private String email;
    private String name;
    private String password;
    private String phone;

}
