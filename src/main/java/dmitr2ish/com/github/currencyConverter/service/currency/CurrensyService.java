package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;

import java.util.List;

public interface CurrensyService {
    void addCurrency(Currency curr);

    void update(Currency curr);

    Currency getById(Long id);

    List<Currency> getAllCurrencies();

    boolean isExist(Currency curr);

    void delete(Currency curr);
}
