package goodbuyning.api_server.global.common.exception;

import goodbuyning.api_server.global.common.response.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SecurityException extends RuntimeException {
    private ErrorCode errorCode;

    public SecurityException() {
        super();
    }

    public SecurityException(String message) {
        super(message);
    }
}
