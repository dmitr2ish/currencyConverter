package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;

import javax.lang.model.element.Name;
import java.time.LocalDate;
import java.util.List;

public interface CurrencyRepository {
    void addCurrency(Currency curr);

    Currency update(Currency curr);

    Currency getById(String id);

    List<Currency> getAllCurrencies();

    List<Currency> getAllCurrenciesByCourseDate(LocalDate date);

    List<Currency> getAllCurrenciesByCourseName(Name name);

    boolean isExist(Currency curr);

    void delete(Currency curr);
}
