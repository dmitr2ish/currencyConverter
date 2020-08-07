package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository {
    void addHistory(History history);

    History update(History history);

    History getById(Long id);

    List<History> getAllHistories();

    List<History> getAllHistoriesByDate(LocalDate date);

    List<History> getAllHistoriesByDateAndCurrencies(LocalDate date, String initialNameCurrency, String targetNameCurrency);

    boolean isExist(History history);

    void delete(History history);
}
