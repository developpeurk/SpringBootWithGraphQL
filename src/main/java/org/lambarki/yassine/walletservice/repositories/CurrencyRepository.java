package org.lambarki.yassine.walletservice.repositories;

import org.lambarki.yassine.walletservice.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,String> {
}
