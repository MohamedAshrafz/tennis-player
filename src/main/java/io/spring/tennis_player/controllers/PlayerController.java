package io.spring.tennis_player.controllers;

import io.spring.tennis_player.Repositories.PlayerJPARepository;
import io.spring.tennis_player.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class PlayerController {

    private final PlayerJPARepository playerRepository;

    @Autowired
    public PlayerController(PlayerJPARepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping("/addPlayer")
    public Player addPlayer(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String nationality,
            @RequestParam Date birthDate,
            @RequestParam int titles) {

        Player newPlayer = new Player(id, name, nationality, birthDate, titles);
        return playerRepository.save(newPlayer);
    }

    @PatchMapping("/updatePlayer")
    public Player updatePlayer(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String nationality,
            @RequestParam Date birthDate,
            @RequestParam int titles) {

        Player updatedPlayer = new Player(id, name, nationality, birthDate, titles);
        return playerRepository.save(updatedPlayer);
    }

    @DeleteMapping("/deletePlayer")
    public void deletePlayer(
            @RequestParam int id) {

        playerRepository.deleteById(id);;
    }
}
