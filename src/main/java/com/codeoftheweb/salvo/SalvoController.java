package com.codeoftheweb.salvo;



import org.aspectj.apache.bcel.util.Play;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    ShipRepository shipRepository;
    @Autowired
    SalvoRepository salvoRepository;
   /* @RequestMapping("/players")
    public List<Player> getPlayer() {
        return repository.findAll();
    }*/

    @RequestMapping("/games")
    public Map<String, Object> getGame(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<>();
        if (isGuest(authentication)) {
            dto.put("player", null);
        } else {
            dto.put("player", PlayerDTO(isAUTH(authentication)));
        }


        dto.put("games", gameRepository.findAll()
                .stream()
                .map(game -> gameDTO(game))
                .collect(toList()));
        return dto;
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
        /*dto.put("scores", game.getGamePlayers()
                .stream()
                .filter(gamePlayer -> gamePlayer.getPlayer().getScore(game)!=null)
                .map(gamePlayer -> scoreDTO(gamePlayer.getPlayer().getScore(game)))
                .collect(toList()));*/

        return dto;
    }


    private Map<String, Object> gamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", PlayerDTO(gamePlayer.getPlayer()));
        if (gamePlayer.getScores() != null) {
            dto.put("score", gamePlayer.getScores().getScore());

        } else {
            dto.put("score", null);
        }
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
    public List<GamePlayer> getGamePlayer() {
        return gamePlayerRepository.findAll();
    }

    @RequestMapping("/game_view/{gameId}")
    public Map<String, Object> gameViewDTO(@PathVariable Long gameId, Authentication authentication) {
        GamePlayer gamePlayer = gamePlayerRepository.getOne(gameId);

        Map<String, Object> dto = new LinkedHashMap<String, Object>();

         if(gamePlayer.getPlayer().getId() == isAUTH(authentication).getId()) {
        dto.put("GameId", gamePlayer.getGame().getId());
        dto.put("created", gamePlayer.getGame().getDate());
        dto.put("Gameplayers", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayerDTO(gamePlayer1)).collect(Collectors.toList()));
        dto.put("Ships", gamePlayer.getShips().stream().map(ship -> shipDTO(ship)).collect(Collectors.toList()));
        dto.put("Salvoes", gamePlayer.getSalvoes().stream().map(salvo -> salvoDTO(salvo)).collect(Collectors.toList()));

        if (oppGamePlayer(gamePlayer) != null) {
            dto.put("EnemySalvoes", oppGamePlayer(gamePlayer).getSalvoes().stream().map(salvo -> salvoDTO(salvo)).collect(Collectors.toList()));
        }
        return dto;
    } else

    {
        return playerInfo("YOU SHALL NOT PASS!", HttpStatus.FORBIDDEN);
    }


}

    private Map<String, Object> shipDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", ship.getType());
        dto.put("location", ship.getLocation());
        return dto;
    }

    private Map<String, Object> salvoDTO(Salvo salvo) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", salvo.getTurn());
        dto.put("position", salvo.getPosition());
        return dto;
    }

    private Map<String, Object> scoreDTO(Score score) {
        System.out.println(score);
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("score", score.getScore());
        dto.put("finishDate", score.getDate());
        dto.put("player", PlayerDTO(score.getPlayer()));
        return dto;
    }

    // method to access opponent information

    public GamePlayer oppGamePlayer(GamePlayer gamePlayer){
        return gamePlayer.getGame().getGamePlayers().stream().filter(gamePlayer1 -> gamePlayer1.getId() != gamePlayer.getId()).findAny().orElse(null);

    }

    @RequestMapping("/leaderboard")
    public Map <String, Object> leaderBoardDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        List<GamePlayer> gamePlayers = gamePlayerRepository.findAll();

        for (GamePlayer gp: gamePlayers) {
            Map<String, Object> scores = new LinkedHashMap<String, Object>();
            if(!scores.containsKey(gp.getPlayer().getUserName())) {
                scores.put("wins", gp.getPlayer().getScores().stream().filter(score -> score.getScore() == 1).count());
                scores.put("losses", gp.getPlayer().getScores().stream().filter(score -> score.getScore() == 0).count());
                scores.put("ties", gp.getPlayer().getScores().stream().filter(score -> score.getScore() == 0.5).count());
                scores.put("total", gp.getPlayer().getScores().stream().mapToDouble(score -> score.getScore()).sum());
                dto.put(gp.getPlayer().getUserName(), scores);

            }


        }

        return dto;

    }
    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestBody Player player) {

        if (player.getUserName().isEmpty() ||  player.getEmail().isEmpty() || player.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (repository.findByEmail(player.getEmail()) !=  null) {
            return new ResponseEntity<>("email already in use", HttpStatus.FORBIDDEN);
        }

        repository.save(new Player(player.getEmail(), player.getUserName(),player.getPassword()));
        return new ResponseEntity<>(playerInfo("userName", player.getUserName()), HttpStatus.CREATED);
    }

    private Map<String, Object> playerInfo (String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }



    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private Player isAUTH (Authentication authentication){
        return repository.findByEmail(authentication.getName());
    }
    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public ResponseEntity<Object> createGame(Authentication authentication) {
        Game game = new Game();
        GamePlayer gamePlayer = new GamePlayer(isAUTH(authentication), game);
        game.addGamePlayer(gamePlayer);
        gameRepository.save(game);
        gamePlayerRepository.save(gamePlayer);
        return new ResponseEntity<>(playerInfo("gpId", gamePlayer.getId()), HttpStatus.CREATED);

    }
    @RequestMapping(path = "/game/{nn}/players", method = RequestMethod.POST)
    public ResponseEntity<Object> joinGame(Authentication authentication, @PathVariable Long nn) {
    if (isAUTH(authentication) == null){
        return new ResponseEntity<>("Must Log in", HttpStatus.FORBIDDEN);

    }
    Game game = gameRepository.getOne(nn);
    Player player = isAUTH(authentication);
    GamePlayer gamePlayer = new GamePlayer(player, game);
    gamePlayerRepository.save(gamePlayer);
    return new ResponseEntity<>(playerInfo("joinId", gamePlayer.getId()), HttpStatus.CREATED);

    }
    @RequestMapping(path = "/games/players/{gamePlayerId}/ships", method = RequestMethod.POST)
    public ResponseEntity<Object> placeShips(Authentication authentication, @PathVariable Long gamePlayerId, @RequestBody Set<Ship> ships){


        GamePlayer gamePlayer = gamePlayerRepository.getOne(gamePlayerId);

        if (isAUTH(authentication) == null){
            return new ResponseEntity<>(playerInfo("Error","Must log in"), HttpStatus.FORBIDDEN);
        }
        if (gamePlayer == null){
            return new ResponseEntity<>(playerInfo("Error","No gameplayer with the given id"),HttpStatus.FORBIDDEN );

        }
        if (isAUTH(authentication).getId() != gamePlayer.getPlayer().getId()){
            return new ResponseEntity<>(playerInfo("Error","this is wrong"),HttpStatus.FORBIDDEN );
        }
        if (gamePlayer.getShips().size() !=0 ) {
            return new ResponseEntity<>(playerInfo("Error","place some ships"),HttpStatus.FORBIDDEN );

        }
        if (ships.size() != 5){
            return new ResponseEntity<>(playerInfo("Error","You need 5 ships soldier"),HttpStatus.FORBIDDEN );

        }
        for (Ship ship : ships){
            gamePlayer.addShip(ship);
            shipRepository.save(ship);
        }
        return new ResponseEntity<>(playerInfo("gamePlayerId",gamePlayer.getId()),HttpStatus.CREATED );

    }
    @RequestMapping(path = "/games/players/{gamePlayerId}/salvos", method = RequestMethod.POST)
    public ResponseEntity<Object> placeSalvos(Authentication authentication, @PathVariable Long gamePlayerId, @RequestBody Salvo salvo) {


        GamePlayer gamePlayer = gamePlayerRepository.getOne(gamePlayerId);
        if (isAUTH(authentication) == null) {
            return new ResponseEntity<>(playerInfo("Error", "Must log in"), HttpStatus.UNAUTHORIZED);
        }
        if (gamePlayer == null) {
            return new ResponseEntity<>(playerInfo("Error", "No gameplayer with the given id"), HttpStatus.UNAUTHORIZED);

        }
        if (isAUTH(authentication).getId() != gamePlayer.getPlayer().getId()) {
            return new ResponseEntity<>(playerInfo("Error", "you don't belong here"), HttpStatus.UNAUTHORIZED);
        }
//        if (salvos.size() != 3) {
//            return new ResponseEntity<>(playerInfo("Error", "Shoot three times"), HttpStatus.FORBIDDEN);
//
//        }
            gamePlayer.addSalvo(salvo);
            salvoRepository.save(salvo);
        return new ResponseEntity<>(playerInfo("gamePlayerId", gamePlayer.getId()), HttpStatus.CREATED);

    }

}
