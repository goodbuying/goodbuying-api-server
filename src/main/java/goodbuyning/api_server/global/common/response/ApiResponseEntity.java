package goodbuyning.api_server.global.common.response;

import org.springframework.http.ResponseEntity;

/**
 * ResponseEntity를 상속받아 API 응답을 간편하게 생성할 수 있는 클래스
 * 내부적으로 ApiResponseUtil을 사용하여 일관된 응답 형식을 제공합니다.
 */
public class ApiResponseEntity<T> extends ResponseEntity<ApiResponse<T>> {

    private ApiResponseEntity(ResponseEntity<ApiResponse<T>> responseEntity) {
        super(responseEntity.getBody(), responseEntity.getHeaders(), responseEntity.getStatusCode());
    }

    /**
     * 성공 응답 생성 (200 OK)
     */
    public static <T> ApiResponseEntity<T> success(T data) {
        return new ApiResponseEntity<>(ApiResponseUtil.success(data));
    }

    /**
     * 성공 응답 생성 (데이터 없음, 200 OK)
     */
    public static <T> ApiResponseEntity<T> success() {
        return new ApiResponseEntity<>(ApiResponseUtil.success());
    }

    /**
     * 실패 응답 생성 (ErrorCode의 HTTP 상태 코드 자동 적용)
     */
    public static <T> ApiResponseEntity<T> failure(ErrorCode errorCode) {
        return new ApiResponseEntity<>(ApiResponseUtil.failure(errorCode));
    }

    /**
     * 실패 응답 생성 (커스텀 메시지 + ErrorCode의 HTTP 상태 코드 자동 적용)
     */
    public static <T> ApiResponseEntity<T> failure(String message, ErrorCode errorCode) {
        return new ApiResponseEntity<>(ApiResponseUtil.failure(message, errorCode));
    }

    /**
     * Created 응답 생성 (201 Created)
     */
    public static <T> ApiResponseEntity<T> created(T data) {
        return new ApiResponseEntity<>(ApiResponseUtil.created(data));
    }

    /**
     * Created 응답 생성 (데이터 없음, 201 Created)
     */
    public static <T> ApiResponseEntity<T> created() {
        return new ApiResponseEntity<>(ApiResponseUtil.created(null));
    }
}
