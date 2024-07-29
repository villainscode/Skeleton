package com.codevillain.backend.entity.enums;

public enum UserRoleEnum {
    GUEST("미로그인"),
    NORMAL("준회원"),
    REGULAR("정회원"),
    ADMIN("운영자");

    private final String name;

    UserRoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}