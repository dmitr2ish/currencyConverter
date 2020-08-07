package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.History;

import java.util.Date;
import java.util.List;

public interface HistoryService {
    void addHistory(History history);

    History update(History history);

    History getById(Long id);

    List<History> getAllHistories();

    List<History> getAllHistoriesByDate(Date date);

    List<History> getAllHistoriesByDateAndCurrencies(Date date, String initialNameCurrency, String targetNameCurrency);

    boolean isExist(History history);

    void delete(History history);
}
