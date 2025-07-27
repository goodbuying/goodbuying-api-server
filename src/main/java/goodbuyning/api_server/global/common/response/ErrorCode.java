package goodbuyning.api_server.global.common.response;

/**
 * API 에러 코드를 정의하는 Enum
 * 각 에러는 고유한 코드와 HTTP 상태 코드를 가집니다.
 */
public enum ErrorCode {
    
    // 1xxx Success
    SUCCESS(1000, 200, "success"),
    
    // 4xx

    INVALID_INPUT(909090, 400, "invalid input"),

    //Domain ErrorCode
    TEST_INVALID(909090,400,"test invalid"),




    //Security ErrorCode
    UNAUTHORIZED(4010, 401, "인증이 필요합니다"),
    INVALID_TOKEN(4011, 401, "유효하지 않은 토큰입니다"),
    EXPIRED_TOKEN(4012, 401, "만료된 토큰입니다"),
    MISSING_AUTHORIZATION_HEADER(4013, 401, "Authorization 헤더가 누락되었습니다"),

    INTERNAL_SERVER_ERROR(5000, 500, "서버 내부 오류가 발생했습니다");

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
