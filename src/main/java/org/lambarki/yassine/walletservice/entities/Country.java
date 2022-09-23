package org.lambarki.yassine.walletservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String countryName;
    private int m49code;
    private String isoCode;
    @ManyToOne
    private Continent continent;
    @ManyToOne
    private Currency currency;
    private double longitude;
    private double latitude;
    private double altitude;

}
