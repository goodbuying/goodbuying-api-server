package goodbuyning.api_server.global.common.exception;

import goodbuyning.api_server.global.common.response.ApiResponse;
import goodbuyning.api_server.global.common.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthorizationException(AuthorizationException e) {
        log.warn("Authorization Exception: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        String message = hasCustomMessage(e, errorCode) ? e.getMessage() : errorCode.getDefaultMessage();
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.failure(message, errorCode));
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiResponse<Void>> handleTokenException(TokenException e) {
        log.warn("Token Exception: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        String message = hasCustomMessage(e, errorCode) ? e.getMessage() : errorCode.getDefaultMessage();
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.failure(message, errorCode));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ApiResponse<Void>> handleSecurityException(SecurityException e) {
        log.warn("Security Exception: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        String message = hasCustomMessage(e, errorCode) ? e.getMessage() : errorCode.getDefaultMessage();
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.failure(message, errorCode));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomainException(DomainException e) {
        log.warn("Domain Exception: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        String message = hasCustomMessage(e, errorCode) ? e.getMessage() : errorCode.getDefaultMessage();
        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.failure(message, errorCode));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ApiResponse<Void>> handleInternalServerException(InternalServerException e) {
        log.error("Internal Server Exception: {}", e.getMessage(), e);
        return ResponseEntity.status(500)
                .body(ApiResponse.failure(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("Illegal Argument Exception: {}", e.getMessage());
        return ResponseEntity.status(400)
                .body(ApiResponse.failure(e.getMessage(), ErrorCode.INVALID_INPUT));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException e) {
        log.error("Runtime Exception: {}", e.getMessage(), e);
        return ResponseEntity.status(500)
                .body(ApiResponse.failure("서버 내부 오류가 발생했습니다", ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("Unexpected Exception: {}", e.getMessage(), e);
        return ResponseEntity.status(500)
                .body(ApiResponse.failure("서버 내부 오류가 발생했습니다", ErrorCode.INTERNAL_SERVER_ERROR));
    }

    private boolean hasCustomMessage(RuntimeException e, ErrorCode errorCode) {
        return e.getMessage() != null && !e.getMessage().equals(errorCode.getDefaultMessage());
    }
}