package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.entity.currency.History;
import dmitr2ish.com.github.currencyConverter.service.currency.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class RestControllerHistory {

    final private HistoryService historyService;

    @Autowired
    public RestControllerHistory(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addHistory(@RequestBody History history) {
        historyService.addHistory(history);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{login}", method = RequestMethod.GET)
    public ResponseEntity<List<History>> getAllHistory(@PathVariable(value = "login") String login) {
        List<History> list = historyService.getAllHistories(login);
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHistory(@RequestBody History history) {
        historyService.delete(history);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAll/{login}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllHistory(@PathVariable(value = "login") String login) {
        historyService.deleteAll(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
