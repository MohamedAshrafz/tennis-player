package io.spring.tennis_player;

import io.spring.tennis_player.DAOs.PlayerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        int id = 3;
        String nationality = "USA";
//        logger.info("All Players Data: {}", playerDAO.getAllPlayers());
//        logger.info("Player with id: {} id: {}", id, playerDAO.getPlayerByID(11));
        logger.info("Player with nationality [{}] are: {}", nationality, playerDAO.getAllPlayersByNationality(nationality));
    }
}
