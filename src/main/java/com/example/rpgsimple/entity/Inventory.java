package com.example.rpgsimple.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    private Integer smoke;

    @ColumnDefault("0")
    private Integer bone;

    @ColumnDefault("false")
    private boolean isKey;

    public Inventory(BigDecimal gold, Integer hpBottle, Integer smoke) {
        this.gold = gold;
        this.hpBottle = hpBottle;
        this.smoke = smoke;
    }
}
