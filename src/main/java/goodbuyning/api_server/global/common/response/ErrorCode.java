package goodbuyning.api_server.global.common.response;

/**
 * API 에러 코드를 정의하는 Enum
 * 각 에러는 고유한 코드와 HTTP 상태 코드를 가집니다.
 */
public enum ErrorCode {
    
    // 1xxx Success
    SUCCESS(1000, 200, "성공"),
    
    // 4xx Client Errors
    INVALID_INPUT(4001, 400, "잘못된 입력 값입니다");

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
