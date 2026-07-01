package com.venkatesh.ai_crm_platform.response;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> ApiResponse<T> success(
            String message,
            T data) {

        return new ApiResponse<>(
                true,
                message,
                data
        );
    }

    public static <T> ApiResponse<T> error(
            String message) {

        return new ApiResponse<>(
                false,
                message,
                null
        );
    }
}