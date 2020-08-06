package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.ValCurse;
import dmitr2ish.com.github.currencyConverter.entity.user.Role;

import java.util.Date;
import java.util.List;

public interface ValCurseRepository {

    ValCurse getByName(String name);

    List<ValCurse> getByDate(Date date);

    List<ValCurse> getAll();

    void add(ValCurse curse);
}
