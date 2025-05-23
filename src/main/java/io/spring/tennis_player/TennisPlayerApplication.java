package io.spring.tennis_player;

import io.spring.tennis_player.Repositories.PlayerJPARepository;
import io.spring.tennis_player.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class TennisPlayerApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlayerJPARepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(TennisPlayerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        int removeID = 3;
        int updateID = 2;
        String nationality = "USA";

//        logger.info("\n\n>> Inserting Player: {}\n", playerRepository.insertOrUpdatePlayer(
//                new Player("Monfils", "France", Date.valueOf("1986-09-01"), 10)));

        logger.info("\n\n>> Getting all Players: {}\n", playerRepository.findAll());

//        logger.info("\n\n>> Updating Player with id[{}] \nresulted: {}\n", updateID, playerRepository.save(
//                new Player(updateID, "Momo", "Egypt", Date.valueOf("1986-09-01"), 10)));

        logger.info("\n\n>> Getting player with id[{}]: {}\n", updateID, playerRepository.findById(updateID));

        logger.info("\n\n>> Getting all Players: {}\n", playerRepository.findAll());

        logger.info("\n\n>> Getting player with nationality[{}] \nresulted: {}\n", nationality, playerRepository.getPlayersByNationality(nationality));
    }
}
