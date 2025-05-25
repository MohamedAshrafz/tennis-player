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

    @GetMapping("{id}")
    public Player getPlayerById(
            @PathVariable int id) {

        return playerService.getPlayerById(id);
    }

    @PutMapping
    public Player addPlayer(
            @RequestBody Player player) {

        return playerService.addPlayer(player);
    }

    @PatchMapping
    public Player updatePlayer(
            @RequestBody Player player) {

        return playerService.updatePlayer(player);
    }

    @PatchMapping("{id}")
    @Transactional
    public Player updatePlayerTitles(
            @PathVariable int id,
            @RequestParam int titles) {

        return playerService.updatePlayerTitles(id, titles);
    }

    @DeleteMapping("{id}")
    public void deletePlayer(
            @PathVariable int id) {

        playerService.deletePlayer(id);
    }
}
