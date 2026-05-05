package re.edu.validator;

public class JwtExceptionCustom extends RuntimeException {
    public JwtExceptionCustom(String message) {
        super(message);
    }
}
