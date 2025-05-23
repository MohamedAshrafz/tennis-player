package io.spring.tennis_player.controllers;

import io.spring.tennis_player.Repositories.PlayerJPARepository;
import io.spring.tennis_player.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class PlayerController {

    private final PlayerJPARepository playerRepository;

    @Autowired
    public PlayerController(PlayerJPARepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public String getAllPlayers(Model model) {
        model.addAttribute("players", playerRepository.findAll());

        return "players.html";
    }

    @PostMapping("/addPlayer")
    public String addPlayer(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String nationality,
            @RequestParam Date birthDate,
            @RequestParam int titles,
            Model model) {

        Player newPlayer = new Player(id, name, nationality, birthDate, titles);
        playerRepository.save(newPlayer);

        model.addAttribute("players", playerRepository.findAll());

        return "players.html";
    }

    @PostMapping("/updatePlayer")
    public String updatePlayer(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String nationality,
            @RequestParam Date birthDate,
            @RequestParam int titles,
            Model model) {

        Player updatedPlayer = new Player(id, name, nationality, birthDate, titles);
        playerRepository.save(updatedPlayer);

        model.addAttribute("players", playerRepository.findAll());

        return "players.html";
    }

    @PostMapping("/deletePlayer")
    public String deletePlayer(
            @RequestParam int id,
            Model model) {
        playerRepository.deleteById(id);

        model.addAttribute("players", playerRepository.findAll());

        return "players.html";
    }
}
