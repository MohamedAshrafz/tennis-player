package io.spring.tennis_player.controllers;

import io.spring.tennis_player.Repositories.PlayerJPARepository;
import io.spring.tennis_player.models.Player;
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

    private final PlayerJPARepository playerRepository;

    @Autowired
    public PlayerController(PlayerJPARepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping()
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping(params = "id")
    public Player getPlayerById(
            @RequestParam int id) {

        logger.info("getPlayerById for id: {}", id);

        return playerRepository.findById(id).orElse(null);
    }

    @PutMapping
    public Player addPlayer(
            @RequestBody Player player) {

        logger.info("addPlayer for player: {}", player);

        return playerRepository.save(player);
    }

    @PatchMapping
    public Player updatePlayer(
            @RequestBody Player player) {

        logger.info("updatePlayer for player: {}", player);

        return playerRepository.save(player);
    }

    @PatchMapping(params = {"id", "titles"})
    @Transactional
    public Player updatePlayerTitles(
            @RequestParam int id,
            @RequestParam int titles) {

        logger.info("updatePlayerTitles for id: {} and titles: {}", id, titles);

        Player playerToUpdate = playerRepository.findById(id).orElse(null);

        if (playerToUpdate == null)
            return null;
        else {
            playerToUpdate.setTitles(titles);
            return playerRepository.save(playerToUpdate);
        }
    }

    @DeleteMapping(params = "id")
    public void deletePlayer(
            @RequestParam int id) {

        logger.info("deletePlayer for id: {}", id);

        playerRepository.deleteById(id);
    }
}
