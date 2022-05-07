package com.example.rpgsimple.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Setter
@Table(name = "character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer lvl;

    private Integer currentHp;
    private Integer hp;
    private Integer attack;
    private Integer speed;

    private Integer currentExperience;
    private Integer scoreExpToNextLvl;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory", referencedColumnName = "id")
    private Inventory inventoryCharacter;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "users", referencedColumnName = "id")
    private User userChatacter;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "class", referencedColumnName = "id")
    private Class classCharacter;
}
