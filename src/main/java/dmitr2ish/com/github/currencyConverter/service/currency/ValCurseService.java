package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.ValCurse;

import java.util.Date;
import java.util.List;

public interface ValCurseService {
    ValCurse getByName(String name);

    List<ValCurse> getByDate(Date date);

    List<ValCurse> getAll();

    void add(ValCurse curse);
}
