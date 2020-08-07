package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.History;
import dmitr2ish.com.github.currencyConverter.repository.currensy.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    final private HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    @Override
    public void addHistory(History history) {
        historyRepository.addHistory(history);
    }

    @Override
    public History update(History history) {
        return historyRepository.update(history);
    }

    @Override
    public History getById(Long id) {
        return historyRepository.getById(id);
    }

    @Override
    public List<History> getAllHistories() {
        return historyRepository.getAllHistories();
    }

    @Override
    public List<History> getAllHistoriesByDate(Date date) {
        return historyRepository.getAllHistoriesByDate(date);
    }

    @Override
    public List<History> getAllHistoriesByDateAndCurrencies(Date date, String initialNameCurrency, String targetNameCurrency) {
        return historyRepository.getAllHistoriesByDateAndCurrencies(date, initialNameCurrency, targetNameCurrency);
    }

    @Override
    public boolean isExist(History history) {
        return historyRepository.isExist(history);
    }

    @Override
    public void delete(History history) {
        historyRepository.delete(history);
    }
}
