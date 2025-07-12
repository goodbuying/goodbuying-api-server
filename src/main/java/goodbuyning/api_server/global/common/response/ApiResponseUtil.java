package goodbuyning.api_server.global.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * API 응답을 생성하는 유틸리티 클래스
 * ErrorCode의 HTTP 상태 코드를 자동으로 적용하여 ResponseEntity를 생성합니다.
 */
public final class ApiResponseUtil {

    private ApiResponseUtil() {
        // 유틸리티 클래스, 인스턴스 생성 방지
    }

    /**
     * 성공 응답 생성 (200 OK)
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    /**
     * 성공 응답 생성 (데이터 없음, 200 OK)
     */
    public static <T> ResponseEntity<ApiResponse<T>> success() {
        return ResponseEntity.ok(ApiResponse.success());
    }

    /**
     * 실패 응답 생성 (ErrorCode의 HTTP 상태 코드 자동 적용)
     */
    public static <T> ResponseEntity<ApiResponse<T>> failure(ErrorCode errorCode) {
        HttpStatus httpStatus = HttpStatus.valueOf(errorCode.getHttpStatusCode());
        return ResponseEntity.status(httpStatus)
                .body(ApiResponse.failure(errorCode));
    }

    /**
     * 실패 응답 생성 (커스텀 메시지 + ErrorCode의 HTTP 상태 코드 자동 적용)
     */
    public static <T> ResponseEntity<ApiResponse<T>> failure(String message, ErrorCode errorCode) {
        HttpStatus httpStatus = HttpStatus.valueOf(errorCode.getHttpStatusCode());
        return ResponseEntity.status(httpStatus)
                .body(ApiResponse.failure(message, errorCode));
    }

    /**
     * Created 응답 생성 (201 Created)
     */
    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(data));
    }

    /**
     * No Content 응답 생성 (204 No Content)
     */
    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success());
    }
}
