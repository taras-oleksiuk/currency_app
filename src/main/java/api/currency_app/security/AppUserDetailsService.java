package api.currency_app.security;

import api.currency_app.model.AppUser;
import api.currency_app.registration.token.ConfirmationToken;
import api.currency_app.repository.AppUserRepository;
import api.currency_app.service.impl.ConfirmationTokenServiceImpl;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger(AppUserDetailsService.class);
    private static final String USER_NOT_FOUND_MSG =
            "User with email %s not found";
    private static final String EMAIL_EXIST = "email already taken";
    private static final int TIME_LIMIT = 15;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenServiceImpl confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        logger.debug("LoadUserByUsername method was call. Params: email = {}", email);
        return appUserRepository.findByEmail(email)
                .orElseThrow(()
                        -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();
        if (userExists) {
           throw new IllegalStateException(EMAIL_EXIST);
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(TIME_LIMIT),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        logger.info("User is signUp");
        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
