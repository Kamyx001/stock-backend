package me.kamyx.stock.repository;

import me.kamyx.stock.model.Stock;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<Stock, ObjectId> {
    Optional<Stock> findByShortName(String shortName);
   // Optional <List<Double>> getPriceHistory(String shortName);
   // void changeCurrentPrice(String shortName, double price);
//    Optional<Double> getCurrentPrice(String shortName);
    //Optional<ObjectId> getcommentIds(String shortName);

}
