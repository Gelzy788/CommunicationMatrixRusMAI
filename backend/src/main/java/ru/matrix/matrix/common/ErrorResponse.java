package ru.matrix.matrix.common;

public record ErrorResponse(String code, String messege) {
    public static ErrorResponse of(ErrorCode error) {
        return new ErrorResponse(error.name(), error.getMessage());
    }
}
