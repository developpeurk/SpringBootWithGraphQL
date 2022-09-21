package org.lambarki.yassine.walletservice.repositories;

import org.lambarki.yassine.walletservice.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,String> {
}
