package io.spring.tennis_player.services;

import io.spring.tennis_player.Repositories.PlayerJPARepository;
import io.spring.tennis_player.exceptions.ResourceNotFoundException;
import io.spring.tennis_player.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Player getPlayerById(int id) throws ResourceNotFoundException {

        logger.info("getPlayerById for id: {}", id);

        return playerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Player with id [%s] not found.", id)));
    }

    public Player addPlayer(Player player) {

        logger.info("addPlayer for player: {}", player);

        return playerRepository.save(player);
    }

    public Player updatePlayer(int id, Player player) throws ResourceNotFoundException {

        logger.info("updatePlayer for player: {}", player);

        if (playerRepository.existsById(id)) {
            player.setId(id);
            return playerRepository.save(player);
        } else
            throw new ResourceNotFoundException(String.format("Player with id [%s] not found.", id));
    }

    public Player updatePlayerTitles(int id, int titles) {

        logger.info("updatePlayerTitles for id: {} and titles: {}", id, titles);

        Optional<Player> optionalPlayer = playerRepository.findById(id);
        Player p;

        if (optionalPlayer.isPresent()) {
            p = optionalPlayer.get();
            p.setTitles(titles);
            return playerRepository.save(p);
        } else
            throw new RuntimeException(String.format("Player with id [%s] not found.", id));
    }

    public void deletePlayer(int id) {

        logger.info("deletePlayer for id: {}", id);

        playerRepository.deleteById(id);
    }


}
