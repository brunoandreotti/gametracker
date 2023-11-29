package com.brunoandreotti.gametracker.domain.models;

import jakarta.persistence.*;

import lombok.Data;



@Entity
@Table(name = "tb_game")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column
    Integer averageRate = 0;

    @Column
    String imageUrl;


}
