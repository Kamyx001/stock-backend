package me.kamyx.stock.controller;

import me.kamyx.stock.model.Stock;
import me.kamyx.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
