package io.spring.tennis_player.Repositories;

import io.spring.tennis_player.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerJPARepository extends JpaRepository<Player, Integer> {

    //    @Query(
//            value = "SELECT * FROM Player p WHERE p.nationality = :nationality",
//            nativeQuery = true
//    )
//    @Query(
//            value = "SELECT p FROM Player p WHERE p.nationality = :nationality"
//    )
    List<Player> getPlayersByNationality(String nationality);

    List<Player> findPlayersByTitles(int titles);

    @Modifying
    @Query("UPDATE Player p SET p.titles = :titles WHERE id = :id")
    void updatePlayerTitles(int id, int titles);
//    public void updatePlayerTitles(@Param("id") int id, @Param("titles") int titles);
}
