package goodbuyning.api_server.global.common.exception;

import goodbuyning.api_server.global.common.response.ErrorCode;
import lombok.Getter;

import java.io.Serial;

@Getter
public class TokenException extends SecurityException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TokenException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
