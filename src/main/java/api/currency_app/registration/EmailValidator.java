package api.currency_app.registration;

import java.util.function.Predicate;
import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {
    private static final String EMAIL_VALIDATION_REGEX = "^(.+)@(.+)$";

    @Override
    public boolean test(String email) {
        return email != null && email.matches(EMAIL_VALIDATION_REGEX);
    }
}
