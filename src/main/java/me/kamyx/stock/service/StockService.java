package me.kamyx.stock.service;

import me.kamyx.stock.Maths;
import me.kamyx.stock.model.Stock;
import me.kamyx.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> allStocks() {
        return stockRepository.findAll();
    }
    public void addPrice(String shortName, double price) {
        Optional<Stock> stock = stockRepository.findByShortName(shortName);
        if (stock.isPresent()) {
            Stock s = stock.get();
            s.setCurrentPrice(price);
            s.getPriceHistory().add(price);
            stockRepository.save(s);
        }
    }
    public List<Double> getPriceHistory(String shortName) {
        Optional<Stock> stock = stockRepository.findByShortName(shortName);
        if (stock.isPresent()) {
            Stock s = stock.get();
            return s.getPriceHistory();
        }

        return null;
    }

//    public void addToPriceHistory(String shortName, double price) {
//        Optional<Stock> stock = stockRepository.findByShortName(shortName);
//        if (stock.isPresent()) {
//            Stock s = stock.get();
//            s.changeCurrentPrice(price);
//            stockRepository.save(s);
//        }
    public double updatePrice(String shortName) {

        List<Double> hPrices = new ArrayList();
            Optional<Stock> stock = stockRepository.findByShortName(shortName);
            if (stock.isPresent()) {
                Stock s = stock.get();
                hPrices = s.getPriceHistory();
            }
            Maths maths = new Maths();
            double meanReturns=maths.meanReturn((ArrayList<Double>) hPrices);
            double standardDReturns=maths.standardDeviation((ArrayList<Double>) hPrices);
            double currentPrice = hPrices.get(hPrices.size()-1);
            //System.out.println(meanReturns);
            //System.out.println(standardDReturns);
            double timeStep = 1.0/400.0; // Ustaw krok czasowy na 1 dzień (przyjmując 252 dni robocze w roku)


            double simulatedPrice = Maths.simulatePrice(currentPrice, meanReturns, standardDReturns, timeStep);
            while (simulatedPrice-currentPrice>0.06*currentPrice || simulatedPrice-currentPrice<-0.06*currentPrice) {
                simulatedPrice = Maths.simulatePrice(currentPrice, meanReturns, standardDReturns, timeStep);
            }

            BigDecimal bg = new BigDecimal(simulatedPrice); // tworzymy obiekt BigDecimal z liczby
            bg = bg.setScale(2, RoundingMode.HALF_UP); // ustawiamy skalę na 2 miejsca po przecinku i tryb zaokrąglania na HALF_UP
            double simulatedPriceFinal = bg.doubleValue(); // pobieramy wartość double z obiektu BigDecimal

            addPrice(shortName,simulatedPriceFinal);


            return simulatedPriceFinal;
    }
    @Scheduled(fixedDelay = 300000)
    public void allStocksUpdatePrice() {
        List<Stock> stocks = allStocks();
        double sum=0;
        for (Stock s : stocks) {
            updatePrice(s.getShortName());
            System.out.println(s.getShortName()+": "+s.getCurrentPrice());
        }
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    public void buyStocks(String shortName, int myShares) {
        Optional<Stock> stock = stockRepository.findByShortName(shortName);
        if (stock.isPresent()) {
            Stock s = stock.get();
            s.setMyShares(s.getMyShares()+myShares);
            stockRepository.save(s);




//            mongoTemplate.update(Stock.class)
//                    .matching(query(where("shortName").is(shortName)))
//                    .apply(update("myShares", s.getMyShares()))
//                    .first();
        }

    }
    public void sellStocks(String shortName, int myShares) {
        Optional<Stock> stock = stockRepository.findByShortName(shortName);
        if (stock.isPresent()) {
            Stock s = stock.get();
            s.setMyShares(s.getMyShares()-myShares);
            stockRepository.save(s);
        }

    }








//    public double updatePrice(String shortName,double mu,double sigma,double dt) {
//        Random random = new Random();
//        //double[] prices = new double[steps];
//        List <Double> hPrices= new ArrayList();
//        Optional<Stock> stock = stockRepository.findByShortName(shortName);
//        if (stock.isPresent()) {
//            Stock s = stock.get();
//            hPrices = s.getPriceHistory();
//        }
//        List <Double> prices= new ArrayList();
//
//
//        Double S0 = hPrices.get(hPrices.size()-1);
//        //prices[0] = S0;
//
//        for (int i = 1; i < 2; i++) {       //steps-test
//            double normalRandom = random.nextGaussian();
//            double driftComponent = (mu - 0.5 * Math.pow(sigma, 2)) * dt;
//            double diffusionComponent = sigma * Math.sqrt(dt) * normalRandom;
//            //prices[i] = prices[i - 1] * Math.exp(driftComponent + diffusionComponent);
//            prices.add(prices.get(i-1)* Math.exp(driftComponent + diffusionComponent));
//
//        }
//
//        return prices.get(0);
//    }








    public Optional<Stock> singleStock(String shortName) {
        return stockRepository.findByShortName(shortName);
    }
}

//  }
