package api.currency_app.service.impl;


import api.currency_app.model.Currency;
import api.currency_app.repository.CurrencyRepository;
import api.currency_app.service.CurrencyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Override
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }
}
