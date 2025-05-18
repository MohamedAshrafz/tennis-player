package io.spring.tennis_player.controllers;

import io.spring.tennis_player.DAOs.PlayerDAO;
import io.spring.tennis_player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerDAO playerDAO;

    @Autowired
    public PlayerController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @GetMapping("players")
    public String getAllPlayers() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Player player: playerDAO.getAllPlayers())
            stringBuilder.append(player).append("<br></br>");

        System.out.println(stringBuilder);

        return stringBuilder.toString();
    }
}
