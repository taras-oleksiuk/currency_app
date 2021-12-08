package api.currency_app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CurrencyTest {
    private Currency currency;
    private String currencyValue = "USD";

    @Test
    void testGetCurrency() {
        Currency currency = new Currency();
        currency.setBase_currency(currencyValue);
        assertEquals(currency.getBase_currency(), currencyValue);
    }
}
