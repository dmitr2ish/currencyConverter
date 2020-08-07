package dmitr2ish.com.github.currencyConverter.service.currencyXml;

import dmitr2ish.com.github.currencyConverter.entity.currency.ValCurse;
import dmitr2ish.com.github.currencyConverter.performanceXml.ValCurseXml;

import java.text.ParseException;

public interface ValCurseXmlService {
    void saveCurse(ValCurseXml curseXml) throws ParseException;
}
