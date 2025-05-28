package io.spring.tennis_player_spring_data_rest;

import io.spring.tennis_player_spring_data_rest.Repositories.PlayerJPARepository;
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
    private PlayerJPARepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(TennisPlayerApplication.class, args);
    }

    @Override
    public void run(String... args) {

        int removeID = 3;
        int updateID = 2;
        int titles = 8;
        String nationality = "USA";

//        initDB();

//        logger.info("\n\n>> Inserting Player: {}\n", playerRepository.insertOrUpdatePlayer(
//                new Player("Monfils", "France", Date.valueOf("1986-09-01"), 10)));

        logger.info("\n\n>> Getting all Players: {}\n", playerRepository.findAll());

//        logger.info("\n\n>> Updating Player with id[{}] \nresulted: {}\n", updateID, playerRepository.save(
//                new Player(updateID, "Momo", "Egypt", Date.valueOf("1986-09-01"), 10)));

        logger.info("\n\n>> Getting player with id[{}]: {}\n", updateID, playerRepository.findById(updateID));

        logger.info("\n\n>> Getting all Players: {}\n", playerRepository.findAll());

        logger.info("\n\n>> Getting player with nationality[{}] \nresulted: {}\n", nationality, playerRepository.getPlayersByNationality(nationality));
        logger.info("\n\n>> Getting player with titles[{}] \nresulted: {}\n", titles, playerRepository.findPlayersByTitles(titles));
    }

//    private void initDB() {
//        playerRepository.save(new Player("Djokovic", "Serbia", Date.valueOf("1987-05-22").toLocalDate(), 81));
//        playerRepository.save(new Player("Monfils", "France", Date.valueOf("1986-09-01").toLocalDate(), 10));
//        playerRepository.save(new Player("Isner", "USA", Date.valueOf("1985-04-26").toLocalDate(), 15));
//        playerRepository.save(new Player("Messi", "Argentina,", Date.valueOf("1994-08-17").toLocalDate(), 8));
//    }
}
