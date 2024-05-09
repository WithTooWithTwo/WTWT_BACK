package wtwt.exception.custom;

public class TokenValidationFailException extends RuntimeException {

    public TokenValidationFailException(String message) {
        super(message);
    }

}
