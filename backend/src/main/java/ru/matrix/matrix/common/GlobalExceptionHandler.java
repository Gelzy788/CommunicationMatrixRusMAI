package ru.matrix.matrix.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // --- 1. Наши ошибки: throw new ApiException(ErrorCode.EMAIL_TAKEN) ---
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApi(ApiException ex) {
        return error(ex.getErrorCode());
    }

    // --- 2. Кривой запрос → VALIDATION_ERROR ---
    @ExceptionHandler({
            MethodArgumentNotValidException.class,        // не прошла проверка @Valid
            HttpMessageNotReadableException.class,        // тело не JSON или JSON сломан
            MethodArgumentTypeMismatchException.class,    // /api/children/abc вместо числа
            MissingServletRequestParameterException.class // нет обязательного параметра
    })
    public ResponseEntity<ErrorResponse> handleValidation(Exception ex) {
        return error(ErrorCode.VALIDATION_ERROR);
    }

    // --- 3. Нет такого адреса → NOT_FOUND ---
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResource(NoResourceFoundException ex) {
        return error(ErrorCode.NOT_FOUND);
    }

    // --- 4. Слишком большое тело (загрузка файлов) → PAYLOAD_TOO_LARGE ---
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleTooLarge(MaxUploadSizeExceededException ex) {
        return error(ErrorCode.PAYLOAD_TOO_LARGE);
    }

    // --- 5. Всё непредвиденное → INTERNAL_ERROR ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
        log.error("Непредвиденная ошибка", ex);   // подробности — только в лог
        return error(ErrorCode.INTERNAL_ERROR);
    }

    private ResponseEntity<ErrorResponse> error(ErrorCode code) {
        return ResponseEntity.status(code.getStatus()).body(ErrorResponse.of(code));
    }
}