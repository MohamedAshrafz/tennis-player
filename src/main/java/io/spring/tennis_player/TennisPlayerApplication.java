package io.spring.tennis_player;

import io.spring.tennis_player.DAOs.PlayerDAO;
import io.spring.tennis_player.model.Player;
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
    private PlayerDAO playerDAO;

    public static void main(String[] args) {
        SpringApplication.run(TennisPlayerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        int removeID = 3;
//
//        Player newPlayer = new Player(8, "Mohamed Ashraf", "asg", Date.valueOf("1999-10-19"), 8);
//
//        logger.info("Getting all players: {}", playerDAO.getAllPlayers());
//
//        logger.info("Inserting a new player {} into the DB ", newPlayer);
//        playerDAO.insertPlayer(newPlayer);
//        logger.info("DB after inserting {}", playerDAO.getAllPlayers());
//
//        logger.info("Remove player with ID: {} Which is: {}into the DB\n", removeID, playerDAO.getPlayerByID(removeID));
//        playerDAO.deletePlayerById(removeID);
//        logger.info("DB after inserting {}", playerDAO.getAllPlayers());
    }
}
