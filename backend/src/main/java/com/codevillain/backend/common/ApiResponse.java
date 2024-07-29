package com.codevillain.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String error;
    private int errorCode;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, 0);
    }

    public static <T> ApiResponse<T> error(String error) {
        return new ApiResponse<>(false, null, error, 0);
    }
    public static <T> ApiResponse<T> error(String error, int errorCode) {
        return new ApiResponse<>(false, null, error, errorCode);
    }

}