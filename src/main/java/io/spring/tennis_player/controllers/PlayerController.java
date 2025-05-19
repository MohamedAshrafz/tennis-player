package io.spring.tennis_player.controllers;

import io.spring.tennis_player.DAOs.PlayerDAO;
import io.spring.tennis_player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class PlayerController {

    private final PlayerDAO playerDAO;

    @Autowired
    public PlayerController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @GetMapping("/players")
    public String getAllPlayers(Model model) {
        model.addAttribute("players", playerDAO.getAllPlayers());

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
        playerDAO.insertPlayer(newPlayer);

        model.addAttribute("players", playerDAO.getAllPlayers());

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
        playerDAO.updatePlayer(updatedPlayer);

        model.addAttribute("players", playerDAO.getAllPlayers());

        return "players.html";
    }

    @PostMapping("/deletePlayer")
    public String deletePlayer(
            @RequestParam int id,
            Model model) {
        playerDAO.deletePlayerById(id);

        model.addAttribute("players", playerDAO.getAllPlayers());

        return "players.html";
    }
}
