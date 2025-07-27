package goodbuyning.api_server.global.common.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends RuntimeException {
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }
}
