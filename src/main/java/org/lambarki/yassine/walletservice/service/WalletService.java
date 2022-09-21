package org.lambarki.yassine.walletservice.service;

import com.github.javafaker.Faker;
import org.lambarki.yassine.walletservice.entities.Currency;
import org.lambarki.yassine.walletservice.entities.Wallet;
import org.lambarki.yassine.walletservice.entities.WalletTransaction;
import org.lambarki.yassine.walletservice.enums.TransactionType;
import org.lambarki.yassine.walletservice.repositories.CurrencyRepository;
import org.lambarki.yassine.walletservice.repositories.WalletRepository;
import org.lambarki.yassine.walletservice.repositories.WalletTransactionRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;


@Service
@Transactional
public class WalletService {
    private CurrencyRepository currencyRepository;
    private WalletRepository walletRepository;
    private WalletTransactionRepository walletTransactionRepository;

    public WalletService(CurrencyRepository currencyRepository, WalletRepository walletRepository, WalletTransactionRepository walletTransactionRepository) {
        this.currencyRepository = currencyRepository;
        this.walletRepository = walletRepository;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    public void loadData() throws IOException {

        Faker faker = new Faker();

        URI uri = new ClassPathResource("/csv/currencies.csv").getURI();
        Path path = Paths.get(uri);
        List<String> lines = Files.readAllLines(path);

        for (int i=1 ;i<lines.size();i++) {
            String[] line = lines.get(i).split(";");
            Currency currency = Currency.builder()
                    .code(line[0])
                    .name(line[1])
                    .saleprice(Double.parseDouble(line[2]))
                    .purchasePrice(Double.parseDouble(line[3]))
                    .build();
            currencyRepository.save(currency);
        }
        Stream.of("MAD","USD","EUR","CAD").forEach(currencyCode->{
            Currency c = currencyRepository.findById(currencyCode)
                            .orElseThrow(()->new RuntimeException(String.format("Currency %s not found", currencyCode)));
            Wallet wallet = new Wallet();
            wallet.setBalance(faker.number().randomDouble(2,1000,9999));
            wallet.setCurrency(c);
            wallet.setCreatedAt(System.currentTimeMillis());
            wallet.setUserId(faker.name().firstName());
            wallet.setId(UUID.randomUUID().toString());
            walletRepository.save(wallet);
        });

        walletRepository.findAll().forEach(wallet -> {

            WalletTransactionAmount(faker, wallet);
        });
    }

    private void WalletTransactionAmount(Faker faker, Wallet wallet) {
        for (int i = 0; i <5 ; i++) {
            WalletTransaction walletTransactionCredit = WalletTransaction.builder()
             .amount(faker.number().randomDouble(2,10000,99999))
                    .wallet(wallet)
                    .type(TransactionType.CREDIT)
                    .timestamp(System.currentTimeMillis())
                    .build();
            walletTransactionRepository.save(walletTransactionCredit);
            wallet.setBalance(wallet.getBalance() + walletTransactionCredit.getAmount());
            walletRepository.save(wallet);
        WalletTransaction walletTransactionDebit = WalletTransaction.builder()
                .amount(faker.number().randomDouble(2,1000,8000))
                .wallet(wallet)
                .timestamp(System.currentTimeMillis())
                .type(TransactionType.DEBIT)
                .build();
            wallet.setBalance(wallet.getBalance()- walletTransactionDebit.getAmount());
            walletTransactionRepository.save(walletTransactionDebit);
            walletRepository.save(wallet);
        }
    }
}
