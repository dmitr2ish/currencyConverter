package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import dmitr2ish.com.github.currencyConverter.entity.currency.ValCurse;
import dmitr2ish.com.github.currencyConverter.repository.currensy.CurrensyRepositroy;
import dmitr2ish.com.github.currencyConverter.repository.currensy.ValCurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CurrencyServiceImplement implements CurrensyService, ValCurseService {
    final private CurrensyRepositroy currensyRepositroy;
    final private ValCurseRepository valCurseRepository;

    @Autowired
    public CurrencyServiceImplement(CurrensyRepositroy currensyRepositroy, ValCurseRepository valCurseRepository) {
        this.currensyRepositroy = currensyRepositroy;
        this.valCurseRepository = valCurseRepository;
    }

    @Override
    public void addCurrency(Currency curr) {
        currensyRepositroy.addCurrency(curr);
    }

    @Override
    public void update(Currency curr) {
        currensyRepositroy.update(curr);
    }

    @Override
    public Currency getById(Long id) {
        return currensyRepositroy.getById(id);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currensyRepositroy.getAllCurrencies();
    }

    @Override
    public boolean isExist(Currency curr) {
        return currensyRepositroy.isExist(curr);
    }

    @Override
    public void delete(Currency curr) {
        currensyRepositroy.delete(curr);
    }

    @Override
    public ValCurse getByName(String name) {
        return valCurseRepository.getByName(name);
    }

    @Override
    public List<ValCurse> getByDate(Date date) {
        return valCurseRepository.getByDate(date);
    }

    @Override
    public List<ValCurse> getAll() {
        return valCurseRepository.getAll();
    }

    @Override
    public void add(ValCurse curse) {
        valCurseRepository.add(curse);
    }
}
