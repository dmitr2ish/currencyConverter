package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository {
    void addHistory(History history);

    History update(History history);

    History getById(Long id);

    List<History> getAllHistories(String login);

    List<History> getAllHistoriesByDate(LocalDate date, String login);

    List<History> getAllHistoriesByDateAndCurrencies(LocalDate date,
                                                     String initialNameCurrency,
                                                     String targetNameCurrency,
                                                     String login);

    boolean isExist(History history);

    void delete(History history);

    void deleteAll(String login);
}
