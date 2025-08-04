package goodbuyning.api_server.global.common.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class InternalServerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }
}
