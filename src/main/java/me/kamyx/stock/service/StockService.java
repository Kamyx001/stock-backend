package me.kamyx.stock.service;

import me.kamyx.stock.model.Stock;
import me.kamyx.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    public List<Stock> allStocks() {
        return stockRepository.findAll();
    }

    public Optional<Stock> singleStock(String shortName) {
        return stockRepository.findByShortName(shortName);
    }
}
