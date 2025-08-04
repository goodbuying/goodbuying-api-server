package goodbuyning.api_server.global.common.response;

/**
 * API 에러 코드를 정의하는 Enum
 * 각 에러는 고유한 코드와 HTTP 상태 코드를 가집니다.
 */
public enum ErrorCode {
    
    // 1xxx Success
    SUCCESS(1000, 200, "success"),
    
    // 4xx - Client Errors
    BAD_REQUEST(4000, 400, "잘못된 요청입니다"),
    INVALID_INPUT(4001, 400, "유효하지 않은 입력값입니다"),
    MISSING_PARAMETER(4002, 400, "필수 파라미터가 누락되었습니다"),

    // 4xx - Authentication & Authorization
    UNAUTHORIZED(4010, 401, "인증이 필요합니다"),
    INVALID_TOKEN(4011, 401, "유효하지 않은 토큰입니다"),
    EXPIRED_TOKEN(4012, 401, "만료된 토큰입니다"),
    MISSING_AUTHORIZATION_HEADER(4013, 401, "Authorization 헤더가 누락되었습니다"),
    FORBIDDEN(4030, 403, "접근 권한이 없습니다"),

    // 4xx - Not Found
    NOT_FOUND(4040, 404, "요청한 리소스를 찾을 수 없습니다"),
    USER_NOT_FOUND(4041, 404, "사용자를 찾을 수 없습니다"),

    // 4xx - Conflict
    CONFLICT(4090, 409, "리소스 충돌이 발생했습니다"),
    DUPLICATE_USER(4091, 409, "이미 존재하는 사용자입니다"),

    // Domain ErrorCode
    TEST_INVALID(6000, 400, "테스트 데이터가 유효하지 않습니다"),

    // 5xx - Server Errors
    INTERNAL_SERVER_ERROR(5000, 500, "서버 내부 오류가 발생했습니다"),
    DATABASE_ERROR(5001, 500, "데이터베이스 오류가 발생했습니다"),
    EXTERNAL_API_ERROR(5002, 500, "외부 API 호출 중 오류가 발생했습니다");

    private final int code;
    private final int httpStatusCode;
    private final String defaultMessage;
    
    ErrorCode(int code, int httpStatusCode, String defaultMessage) {
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.defaultMessage = defaultMessage;
    }
    
    public int getCode() {
        return code;
    }
    
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
    
    public String getDefaultMessage() {
        return defaultMessage;
    }
}
