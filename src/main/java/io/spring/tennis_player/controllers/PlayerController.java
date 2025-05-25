package io.spring.tennis_player.controllers;

import io.spring.tennis_player.models.Player;
import io.spring.tennis_player.services.PlayerService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(params = "id")
    public Player getPlayerById(
            @RequestParam int id) {

        return playerService.getPlayerById(id);
    }

    @PostMapping
    public Player addPlayer(
            @RequestBody Player player) {

        return playerService.addPlayer(player);
    }

    @PutMapping(params = "id")
    public Player updatePlayer(
            @RequestParam int id,
            @RequestBody Player player) throws Exception {

        return playerService.updatePlayer(id, player);
    }

    @PatchMapping(params = {"id", "titles"})
    @Transactional
    public Player updatePlayerTitles(
            @RequestParam int id,
            @RequestParam int titles) throws Exception {

        return playerService.updatePlayerTitles(id, titles);
    }

    @DeleteMapping(params = "id")
    public void deletePlayer(
            @RequestParam int id) {

        playerService.deletePlayer(id);
    }
}
