package me.kamyx.stock.controller;


import me.kamyx.stock.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @GetMapping
    public ResponseEntity<Double> getBalace() {
        return new ResponseEntity<Double>(walletService.getBalance(), HttpStatus.OK);
    }








}
