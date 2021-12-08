package api.currency_app.repository;


import api.currency_app.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends
        JpaRepository<Currency, Long> {
}
