package api.currency_app.service.impl;


import api.currency_app.model.AppUser;
import api.currency_app.model.Role;
import api.currency_app.registration.EmailValidator;
import api.currency_app.registration.RegistrationRequest;
import api.currency_app.registration.token.ConfirmationToken;
import api.currency_app.security.AppUserDetailsService;
import api.currency_app.service.RegistrationService;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private static final Logger logger = LogManager.getLogger(RegistrationServiceImpl.class);
    private final AppUserDetailsService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenServiceImpl confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                Role.RoleName.USER
        ));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

    if (confirmationToken.getConfirmedAt() != null) {
        throw new IllegalStateException("An email already confirmed");
    }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(LocalDateTime.now())) {
        throw new IllegalStateException("token expired");
    }
    confirmationTokenService.setConfirmedAt(token);
    appUserService.enableAppUser(
            confirmationToken.getAppUser().getEmail());
    logger.info("Token is confirmed");
    return "Confirmed";
    }
}
