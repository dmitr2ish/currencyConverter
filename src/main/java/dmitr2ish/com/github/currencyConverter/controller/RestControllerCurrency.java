package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import dmitr2ish.com.github.currencyConverter.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class RestControllerCurrency {

    final private CurrencyService currencyService;

    @Autowired
    public RestControllerCurrency(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Currency> getCurrency(@PathVariable(value = "id") Long id) {
        Currency currency = currencyService.getById(id);
        return (currency != null)
                ? new ResponseEntity<>(currency, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
