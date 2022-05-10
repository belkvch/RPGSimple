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
@Table(name = "enemy")
public class Enemy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer currentHp;
    private Integer hp;
    private Integer attack;
    private Integer speed;

    private Integer exp;

    private BigDecimal gold;

    private String name;

    private String description;

    private String img;
}
