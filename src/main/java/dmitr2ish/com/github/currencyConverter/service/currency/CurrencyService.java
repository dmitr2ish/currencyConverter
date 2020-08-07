package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;

import javax.lang.model.element.Name;
import java.util.Date;
import java.util.List;

public interface CurrencyService {
    void addCurrency(Currency curr);

    void update(Currency curr);

    Currency getById(Long id);

    List<Currency> getAllCurrencies();

    List<Currency> getAllCurrenciesByCourseDate(Date date);

    List<Currency> getAllCurrenciesByCourseName(Name name);

    boolean isExist(Currency curr);

    void delete(Currency curr);
}
