package org.lambarki.yassine.walletservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lambarki.yassine.walletservice.enums.TransactionType;

import javax.persistence.*;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class WalletTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timestamp;
    private Double amount;
    private Double currentSaleCurrencyPrice;
    private Double currentPurchaseCurrencyPrice;
    @ManyToOne
    private Wallet wallet;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

}
