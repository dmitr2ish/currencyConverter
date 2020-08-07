package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import dmitr2ish.com.github.currencyConverter.entity.currency.History;

import javax.lang.model.element.Name;
import java.util.Date;
import java.util.List;

public interface HistoryRepository {
    void addHistory(History history);

    History update(History history);

    History getById(Long id);

    List<History> getAllHistories();

    boolean isExist(History history);

    void delete(History history);
}
