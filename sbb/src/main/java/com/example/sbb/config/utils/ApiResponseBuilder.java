package com.example.sbb.config.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class ApiResponseBuilder {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApiResponse<T> {
        private final String status;
        private final T data;
        private final String message;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, data, null);
    }

    public static <T> ApiResponse<T> successWithNoContent() {
        return new ApiResponse<>(SUCCESS_STATUS, null, null);
    }

    public static ApiResponse<?> fail(Map<String, String> errors, String message) {
        return new ApiResponse<>(FAIL_STATUS, errors, message);
    }

    public static ApiResponse<?> error(String message) {
        return new ApiResponse<>(ERROR_STATUS, null, message);
    }
}