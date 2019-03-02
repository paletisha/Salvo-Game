package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Integer turn;

    @ElementCollection
    @Column(name = "salvoPosition")
    private List<String> position = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gameplayer_id")
    private GamePlayer gamePlayer;


    public Salvo () {

    }


    public Salvo (Integer turn, GamePlayer gamePlayer, List<String> position) {
        this.id = id;
        this.turn = turn;
        this.gamePlayer= gamePlayer;
        this.position=position;



    }

    public Integer getTurn(){
        return turn;
    }

    public void setType(Integer turn) {
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }


    @JsonIgnore
    public GamePlayer getGamePlayer(){
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }




}

