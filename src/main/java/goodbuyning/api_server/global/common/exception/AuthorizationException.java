package goodbuyning.api_server.global.common.exception;

public class AuthorizationException extends SecurityException {
    private static final long serialVersionUID = 1L;

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
