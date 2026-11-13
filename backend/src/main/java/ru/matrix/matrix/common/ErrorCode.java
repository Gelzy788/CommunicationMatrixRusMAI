package ru.matrix.matrix.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Проверьте правильность введённых данных"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Требуется вход в систему"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Неверный email или пароль"),
    CSRF_INVALID(HttpStatus.FORBIDDEN, "Сессия устарела. Обновите страницу и повторите действие"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Объект не найден"),
    EMAIL_TAKEN(HttpStatus.CONFLICT, "Пользователь с таким email уже зарегистрирован"),
    ALREADY_SENT(HttpStatus.CONFLICT, "Это прохождение уже отправлено в логоцентр"),
    PAYLOAD_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "Слишком большой объём данных"),
    MAIL_SEND_FAILED(HttpStatus.BAD_GATEWAY, "Не удалось отправить письмо. Попробуйте позже"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Что-то пошло не так. Попробуйте позже");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() { return status; }
    public String getMessage() { return message; }
}