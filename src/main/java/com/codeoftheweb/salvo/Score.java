package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Double score;
    private Date    date;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;

    public Score(){

    }

    public Score(Double score, Date date, Game game, Player player) {

        this.score = score;
        this.date = date;
        this.game = game;
        this.player = player;
    }


    public void setScore(Double score) {
        this.score = score;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Double getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }
}
