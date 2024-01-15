package me.kamyx.stock.controller;

import me.kamyx.stock.model.Stock;
import me.kamyx.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {
    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<List<Stock>>(stockService.allStocks(), HttpStatus.OK);
    }

    @GetMapping("/{shortName}")
    public ResponseEntity<Optional<Stock>> getStockById(@PathVariable("shortName") String shortName) {
        return new ResponseEntity<Optional<Stock>>(stockService.singleStock(shortName), HttpStatus.OK);
    }

//    @PostMapping("/buyShares")
//    public ResponseEntity<String> buyShares(@RequestBody Map<String, Integer> payload) {
//        return new ResponseEntity<Stock>(stockService.buyStocks(payload.get("shortName"), Integer.parseInt(String.valueOf(payload.get("shares")))), HttpStatus.OK);
//    }
//    @PostMapping("/buyShares")
//    public ResponseEntity<String> buyShares(@RequestBody String shortName, @RequestBody int shares) {
//        stockService.buyStocks(shortName, shares);
//        return new ResponseEntity<>("Transaction Complete",HttpStatus.OK);
//    }

    @PostMapping("/buyShares")
    public ResponseEntity<String> buyShares(@RequestBody Map<String, String> payload) {
        stockService.buyStocks(payload.get("shortName"), Integer.parseInt(payload.get("shares")));
        return new ResponseEntity<>("Transaction Complete", HttpStatus.OK);
    }
}

