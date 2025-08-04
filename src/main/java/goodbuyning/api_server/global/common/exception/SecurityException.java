package goodbuyning.api_server.global.common.exception;

import goodbuyning.api_server.global.common.response.ErrorCode;
import lombok.Getter;

import java.io.Serial;

@Getter
public class SecurityException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final ErrorCode errorCode;

    public SecurityException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public SecurityException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
