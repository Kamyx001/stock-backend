package me.kamyx.stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    private ObjectId id;
    private String username;
    private double balance;
}
