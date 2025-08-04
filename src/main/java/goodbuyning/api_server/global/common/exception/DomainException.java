package goodbuyning.api_server.global.common.exception;

import goodbuyning.api_server.global.common.response.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class DomainException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;

    public DomainException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public DomainException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
