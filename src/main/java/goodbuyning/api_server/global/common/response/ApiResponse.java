package goodbuyning.api_server.global.common.response;

/**
 * API 공통 응답 형식을 나타내는 record
 *
 * @param <T>     응답 데이터 타입
 * @param result    응답 데이터
 * @param code    응답 코드 (성공: 1000, 실패: 4xxx, 5xxx, 6xxx)
 * @param message 응답 메시지 (실패 시에만 존재)
 */
public record ApiResponse<T>(
        int code,
        T result,
        String message
) {
    /**
     * 성공 응답 생성 (데이터 포함)
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), data, null);
    }

    /**
     * 성공 응답 생성 (데이터 없음)
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), null, ErrorCode.SUCCESS.getDefaultMessage());
    }

    /**
     * 실패 응답 생성 (ErrorCode 사용 - 기본 메시지)
     */
    public static <T> ApiResponse<T> failure(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(), null, errorCode.getDefaultMessage());
    }

    /**
     * 실패 응답 생성 (ErrorCode 사용 - 커스텀 메시지)
     */
    public static <T> ApiResponse<T> failure(String message, ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(), null, message);
    }

    /**
     * 실패 응답 생성 (직접 코드 지정)
     */
    public static <T> ApiResponse<T> failure(String message, int code) {
        return new ApiResponse<>(code, null, message);
    }
}
