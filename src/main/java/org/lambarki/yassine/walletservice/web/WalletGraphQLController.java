package org.lambarki.yassine.walletservice.web;

import org.lambarki.yassine.walletservice.dtos.AddWalletRequestDTO;
import org.lambarki.yassine.walletservice.entities.Currency;
import org.lambarki.yassine.walletservice.entities.Wallet;
import org.lambarki.yassine.walletservice.entities.WalletTransaction;
import org.lambarki.yassine.walletservice.repositories.CurrencyRepository;
import org.lambarki.yassine.walletservice.repositories.WalletRepository;
import org.lambarki.yassine.walletservice.service.WalletService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WalletGraphQLController {
    private WalletRepository walletRepository;
    private WalletService walletService;
    private CurrencyRepository currencyRepository;


    public WalletGraphQLController(WalletRepository walletRepository, WalletService walletService, CurrencyRepository currencyRepository) {
        this.walletRepository = walletRepository;
        this.walletService = walletService;
        this.currencyRepository = currencyRepository;
    }

    @QueryMapping
    public List<Currency> currencies (){
        return currencyRepository.findAll();
    }

    @QueryMapping
    private List<Wallet> userWallets(){
        return walletRepository.findAll();
    }


    @QueryMapping
    private Wallet walletById(@Argument String id){
        return walletRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Wallet %s not found",id)));
    }

    @MutationMapping
    private Wallet addWallet(@Argument AddWalletRequestDTO walletDto){
        return walletService.save(walletDto);
    }


    @MutationMapping
    private List<WalletTransaction> walletTransfer(@Argument String sourceWalletId, @Argument String destinationWalletId, @Argument Double amount){
        return walletService.walletTransfer(sourceWalletId,destinationWalletId,amount);

    }
}


