package me.kamyx.stock.repository;

import me.kamyx.stock.model.Stock;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<Stock, ObjectId> {
    Optional<Stock> findByShortName(String shortName);
}
