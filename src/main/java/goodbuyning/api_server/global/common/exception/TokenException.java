package goodbuyning.api_server.global.common.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends SecurityException {
    public TokenException() {
        super();
    }

    public TokenException(String message) {
        super(message);
    }
}
