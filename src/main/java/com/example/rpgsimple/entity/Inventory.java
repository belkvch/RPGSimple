package com.example.rpgsimple.entity;

import lombok.*;

import javax.persistence.*;
import java.math.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Setter
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal gold;

    private Integer hpBottle;

    public Inventory(BigDecimal gold, Integer hpBottle) {
        this.gold = gold;
        this.hpBottle = hpBottle;
    }
}
