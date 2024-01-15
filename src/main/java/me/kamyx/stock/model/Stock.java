package me.kamyx.stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    private ObjectId id;
    private String name;
    private String shortName;
    private double currentPrice;
    private List<Double> priceHistory;
    private double myShares;
    @DocumentReference
    private List<Comment> commentIds;

    public void changeCurrentPrice(double price) {
        this.currentPrice = price;
        this.priceHistory.add(price);
    }

    public void changeMyShares(double shares) {
        this.myShares = shares;
    }

}
