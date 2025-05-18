package io.spring.tennis_player.DAOs;

import io.spring.tennis_player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDAO {

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
}
