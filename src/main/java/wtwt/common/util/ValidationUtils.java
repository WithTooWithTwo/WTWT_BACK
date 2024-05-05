package wtwt.common.util;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationUtils {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&^]).{8,}$");

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+[.][0-9A-Za-z]+$");

    public static void validateEmail(String input, String message) {
        pattern(input, EMAIL_PATTERN, message);
    }

    public static void validatePassword(String input, String message) {
        pattern(input, PASSWORD_PATTERN, message);
    }

    private static void pattern(String input, Pattern pattern, String message) {
        Assert.isTrue(pattern.matcher(input).matches(), message);
    }
}
