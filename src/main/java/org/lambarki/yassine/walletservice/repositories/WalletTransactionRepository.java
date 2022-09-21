package org.lambarki.yassine.walletservice.repositories;

import org.lambarki.yassine.walletservice.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {
}
