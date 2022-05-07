package com.example.rpgsimple.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Setter
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer priorityHP;
    private Integer priorityAttack;
    private Integer prioritySpeed;

    @Column(name = "hpstat")
    private Integer hpStat;
    private Integer attackStat;
    private Integer speedStat;

    private String img;
}
