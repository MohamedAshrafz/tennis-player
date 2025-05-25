package io.spring.tennis_player.services;

import io.spring.tennis_player.Repositories.PlayerJPARepository;
import io.spring.tennis_player.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PlayerJPARepository playerRepository;

    @Autowired
    public PlayerService(PlayerJPARepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) {

        logger.info("getPlayerById for id: {}", id);

        return playerRepository.findById(id).orElse(null);
    }

    public Player addPlayer(Player player) {

        logger.info("addPlayer for player: {}", player);

        return playerRepository.save(player);
    }

    public Player updatePlayer(Player player) {

        logger.info("updatePlayer for player: {}", player);

        return playerRepository.save(player);
    }

    public Player updatePlayerTitles(int id, int titles) {

        logger.info("updatePlayerTitles for id: {} and titles: {}", id, titles);

        Player playerToUpdate = playerRepository.findById(id).orElse(null);

        if (playerToUpdate == null)
            return null;
        else {
            playerToUpdate.setTitles(titles);
            return playerRepository.save(playerToUpdate);
        }
    }

    public void deletePlayer(int id) {

        logger.info("deletePlayer for id: {}", id);

        playerRepository.deleteById(id);
    }


}
