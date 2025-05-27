package io.spring.tennis_player.controllers;

import io.spring.tennis_player.models.Player;
import io.spring.tennis_player.services.PlayerService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(params = "id")
    public Player getPlayerById(
            @RequestParam("id" /* for different naming */) int playerId) {

        return playerService.getPlayerById(playerId);
    }

    @PostMapping
//    @RequestMapping(method = RequestMethod.POST)
    public Player addPlayer(
            @RequestBody Player player) {
        player.setId(0); // To ensure that if the client mistakenly tried to Post with id it's still mapped to add not update

        return playerService.addPlayer(player);
    }

    @PutMapping(params = "id")
    public Player updatePlayer(
            @RequestParam int id,
            @RequestBody /* binds the JSON data to the Player object player */ Player player) {

        return playerService.updatePlayer(id, player);
    }

    @PatchMapping(params = "id")
    @Transactional
    public Player patchPlayer(
            @RequestParam int id,
            @RequestBody Map<String, Object> fields) {

        return playerService.patchPlayer(id, fields);
    }

    @PatchMapping(path = "updateTitles", params = {"id"})
    @Transactional
    public Player updatePlayerTitles(
            @RequestParam int id,
            @RequestBody int titles) {

        return playerService.updatePlayerTitles(id, titles);
    }

    @DeleteMapping(params = "id")
    public String deletePlayer(
            @RequestParam int id) {

        return playerService.deletePlayer(id);
    }
}
