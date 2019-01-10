package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private PlayerRepository repository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/players")
    public List<Player> getPlayer() {
        return repository.findAll();
    }

    @RequestMapping("/games")
    public List <Object> getGame() {
        return gameRepository.findAll()
                .stream()
                .map(game -> gameDTO(game))
                .collect(toList());

    }

    private Map<String, Object> gameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("date", game.getDate());
        dto.put("gameplayers", game.getGamePlayers()
        .stream()
        .map(gamePlayer -> gamePlayerDTO(gamePlayer))
        .collect(toList())
        );
        return dto;
    }




    private Map<String, Object> gamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", gamePlayer.getPlayer());
        return dto;
    }

    private Map<String, Object> PlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("userName", player.getUserName());
        dto.put("email", player.getEmail());
        return dto;
    }


    @RequestMapping("/gamePlayers")
    public List <GamePlayer> getGamePlayer() {
        return gamePlayerRepository.findAll();
    }

    @RequestMapping("/game_view/{gameId}")
    public Map <String, Object> gameViewDTO(@PathVariable Long gameId) {
       GamePlayer gamePlayer = gamePlayerRepository.getOne(gameId);

       Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("GameId", gamePlayer.getGame().getId());
        dto.put("created", gamePlayer.getGame().getDate());
        dto.put("Gameplayers", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayerDTO(gamePlayer1)).collect(Collectors.toList()));
        return dto;

    }


}
