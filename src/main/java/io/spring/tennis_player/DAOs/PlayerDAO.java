package io.spring.tennis_player.DAOs;

import io.spring.tennis_player.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class PlayerDAO {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Player> getAllPlayers() {
        String sql = "SELECT * FROM PLAYER";
        // query: returns a list of objects
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Player.class));
    }

    public List<Player> getAllPlayersByNationality(String nationality) {
        String sql = "SELECT * FROM PLAYER WHERE NATIONALITY = ?";
        // query: returns a list of objects
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Player.class), nationality);
    }

    public Player getPlayerByID(int id) {
        String sql = "SELECT * FROM PLAYER WHERE ID = ?";
        // queryForObject: returns only one object
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Player.class), id);
    }

    public int insertPlayer(Player player) {
        String sql = "INSERT INTO PLAYER (ID, Name, Nationality, Birth_date, Titles) " +
                "VALUES (?, ?, ?, ?, ?)";
        int result = 0;

        try {
            result = jdbcTemplate.update(sql, player.getId(), player.getName(), player.getNationality(),
                    new Timestamp(player.getBirthDate().getTime()), player.getTitles());
        } catch (DataAccessException e) {
            logger.warn(e.getMessage());
        }

        logger.info("insertPlayer [{}] into the db .. result is: {}", player, result);
        return result;
    }

    public int updatePlayer(Player player) {
        String sql = "UPDATE PLAYER SET Name = ?, Nationality = ?, Birth_date = ?, Titles = ? " +
                "WHERE ID = ?";
        int result = 0;

        try {
            result = jdbcTemplate.update(sql, player.getName(), player.getNationality(),
                    new Timestamp(player.getBirthDate().getTime()), player.getTitles(), player.getId());
        } catch (DataAccessException e) {
            logger.warn(e.getMessage());
        }

        logger.info("updatePlayer [{}] .. result is: {}", player, result);
        return result;
    }

    public int deletePlayerById(int id) {
        String sql = "DELETE FROM PLAYER WHERE ID = ?";


        int result = 0;

        try {
            result = jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.warn(e.getMessage());
        }

        logger.info("deletePlayer with id [{}] from the db .. result is: {}", id, result);
        return result;
    }
}
