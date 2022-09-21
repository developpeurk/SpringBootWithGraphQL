package org.lambarki.yassine.walletservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Currency {
    @Id
    private String code;
    private String name;
    private String symbol;
    private Double saleprice;
    private Double purchasePrice;
}
