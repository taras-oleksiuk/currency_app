package api.currency_app.service;

import api.currency_app.model.Currency;
import java.util.List;

public interface CurrencyService {
    void save(Currency currency);

    List<Currency> findAll();
}
