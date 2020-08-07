package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;

import java.util.List;

public interface CurrensyRepositroy {
    void addCurrency(Currency curr);

    Currency update(Currency curr);

    Currency getById(Long id);

    List<Currency> getAllCurrencies();

    boolean isExist(Currency curr);

    void delete(Currency curr);
}
