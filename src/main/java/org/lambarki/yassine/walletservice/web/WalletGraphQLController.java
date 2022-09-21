package org.lambarki.yassine.walletservice.web;

import org.lambarki.yassine.walletservice.entities.Wallet;
import org.lambarki.yassine.walletservice.repositories.WalletRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WalletGraphQLController {
    private WalletRepository walletRepository;

    public WalletGraphQLController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @QueryMapping
    private List<Wallet> userWallets(){
        return walletRepository.findAll();
    }
}
