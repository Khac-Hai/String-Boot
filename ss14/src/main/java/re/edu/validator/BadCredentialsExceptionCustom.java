package re.edu.validator;

public class BadCredentialsExceptionCustom extends RuntimeException {
    public BadCredentialsExceptionCustom(String message) {
        super(message);
    }
}
