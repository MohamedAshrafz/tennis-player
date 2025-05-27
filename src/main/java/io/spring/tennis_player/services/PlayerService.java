package io.spring.tennis_player.services;

import io.spring.tennis_player.Repositories.PlayerJPARepository;
import io.spring.tennis_player.exceptions.PlayerNotFoundException;
import io.spring.tennis_player.exceptions.ResourceNotFoundException;
import io.spring.tennis_player.models.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class PlayerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PlayerJPARepository playerRepository;

//    @PersistenceContext
//    private EntityManager entityManager;

    @Autowired
    public PlayerService(PlayerJPARepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) throws ResourceNotFoundException {

        logger.info("getPlayerById for id: {}", id);

        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(String.format("Player with id [%s] not found.", id)));
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
            throw new PlayerNotFoundException(String.format("Cannot update, Player with id [%s] not found.", id));
    }

    public Player patchPlayer(int id, Map<String, Object> playerPatch) {

        logger.info("patchPlayer for id: {} with fields: {}", id, playerPatch);

        Optional<Player> optionalPlayer = playerRepository.findById(id);

        if (optionalPlayer.isEmpty())
            throw new PlayerNotFoundException(String.format("Cannot update, Player with id [%s] not found.", id));
        /// /////////////////////// if thrown we will top here

        Player playerToUpdate = optionalPlayer.get();
        // if you need the persistent context to ignore this entity and don't update it automatically
//            entityManager.detach(playerToUpdate);
        playerPatch.forEach((key, value) -> {
            Field fieldToUpdate = ReflectionUtils.findField(Player.class, key);
            if (fieldToUpdate != null && !Objects.equals(key, "id")) {
                ReflectionUtils.makeAccessible(fieldToUpdate);
                if (Objects.equals(key, "birthDate")) {
                    ReflectionUtils.setField(fieldToUpdate, playerToUpdate, fromStringToLocalDate((String) value));
                } else
                    ReflectionUtils.setField(fieldToUpdate, playerToUpdate, value);
            }
        });

        // Isn't needed because of @Transactional
//            playerRepository.save(playerToUpdate);
        return playerToUpdate;
    }

    public Player updatePlayerTitles(int id, int titles) {

        logger.info("updatePlayerTitles for id: {} and titles: {}", id, titles);

        Optional<Player> optionalPlayer = playerRepository.findById(id);

        if (optionalPlayer.isEmpty())
            throw new PlayerNotFoundException(String.format("Cannot update, Player with id [%s] not found.", id));
        /// /////////////////////// if thrown we will top here

        playerRepository.updatePlayerTitles(id, titles);
        return optionalPlayer.get();
    }

    public String deletePlayer(int id) {

        logger.info("deletePlayer for id: {}", id);

        Optional<Player> optionalPlayer = playerRepository.findById(id);

        if (optionalPlayer.isPresent())
            throw new PlayerNotFoundException(String.format("Cannot delete, Player with id [%s] not found.", id));
        /// /////////////////////// if thrown we will top here

        playerRepository.deleteById(id);
        return String.format("Player with id [%d] wad deleted", id);
    }

    private static LocalDate fromStringToLocalDate(String strDate) {
        // 1. Define the formatter for your specific input string pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            // 2. Parse the string into a LocalDate object
            return LocalDate.parse(strDate, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date string '" + strDate + "' with format dd-MM-yyyy. " + e.getMessage());
            // Handle the error as appropriate for your application
            // (e.g., return null, throw a custom exception, log and continue)
        }
        return null;
    }
}
