package me.kamyx.stock.service;

import me.kamyx.stock.model.Stock;
import me.kamyx.stock.model.Wallet;
import me.kamyx.stock.repository.StockRepository;
import me.kamyx.stock.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
//    public Wallet getByUsername(String username){
//        walletRepository=
//
//    }
    public void subtractBalance(double amount) {
        Optional<Wallet> wallet = walletRepository.findByUsername("User1");
        if (wallet.isPresent()) {
            Wallet w = wallet.get();
            w.setBalance(w.getBalance() - amount);
            walletRepository.save(w);
        }
    }
    public void addBalance(double amount) {
        Optional<Wallet> wallet = walletRepository.findByUsername("User1");
        if (wallet.isPresent()) {
            Wallet w = wallet.get();
            w.setBalance(w.getBalance() + amount);
            walletRepository.save(w);
        }
    }
    public double getBalance() {
        Optional<Wallet> wallet = walletRepository.findByUsername("User1");
        if (wallet.isPresent()) {
            Wallet w = wallet.get();
            return w.getBalance();
        }
        return 0;
    }



}
