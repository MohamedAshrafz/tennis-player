package io.spring.tennis_player.Repositories;

import io.spring.tennis_player.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerJPARepository extends JpaRepository<Player, Integer> {

    //    @Query(
//            value = "SELECT * FROM Player p WHERE p.nationality = :nationality",
//            nativeQuery = true
//    )
    @Query(
            value = "SELECT p FROM Player p WHERE p.nationality = :nationality"
    )
    public List<Player> getPlayersByNationality(String nationality);
}
