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

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ApiResponse<Void>> handleSecurityException(SecurityException e) {
        log.warn("Security Exception: {}", e.getMessage());
        return ResponseEntity.status(401)
                .body(ApiResponse.failure(e.getMessage(), e.getErrorCode()));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomainException(DomainException e) {
        log.warn("Domain Exception: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(e.getErrorCode().getHttpStatusCode())
                .body(ApiResponse.failure(errorCode));
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("Unexpected Exception: {}", e.getMessage(), e);
        return ResponseEntity.status(500)
                .body(ApiResponse.failure("서버 내부 오류가 발생했습니다", ErrorCode.INTERNAL_SERVER_ERROR));
    }
}