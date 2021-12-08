package api.currency_app.service;

import api.currency_app.registration.RegistrationRequest;

public interface RegistrationService {

    String register(RegistrationRequest request);

    String confirmToken(String token);
}
