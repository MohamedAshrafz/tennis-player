package io.spring.tennis_player.Repositories;

import io.spring.tennis_player.models.Player;
import io.spring.tennis_player.models.QueryNames;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional // Transactions are implemented at the business layer, but in this example, we will implement them at the repository level.
public class PlayerRepository {

    @PersistenceContext
    EntityManager entityManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<Player> getAllPlayers() {
        TypedQuery<Player> getAllQuery = entityManager.createNamedQuery(QueryNames.GET_ALL_PLAYERS, Player.class);

        return getAllQuery.getResultList();
    }

    public List<Player> getPlayersByNationality(String nationality) {
        //         Using JPQL with normal query
        //    "e" is an alias for the entity.
        String jpql = "SELECT p FROM Player p WHERE p.nationality = :nationality"; // ?????

        // 2. Create a TypedQuery object from the JPQL string and the entity class.
        //    Using TypedQuery gives you type safety for the results.
        TypedQuery<Player> query = entityManager.createQuery(jpql, Player.class);
        // 3. Setting the parameter
        query.setParameter("nationality", nationality);

        // 3. Execute the query and get the list of results.
        return query.getResultList();

//        // Using CriteriaQuery, type-safe way to build queries.
//        // It's more verbose than JPQL but offers better compile-time checking and is useful for dynamically building complex queries.
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
//
//        Root<Player> parentRoot = criteriaQuery.from(Player.class);
//
//        Predicate nationalityPredicate = criteriaBuilder.equal(parentRoot.get("nationality"), nationality);
//
//        criteriaQuery.where(nationalityPredicate);
//
//        return entityManager.createQuery(criteriaQuery).getResultList();

        // Using JPQL with NamedQuery
//        TypedQuery<Player> getAllQuery = entityManager.createNamedQuery(QueryNames.GET_PLAYERS_WITH_NATIONALITY, Player.class);
//        getAllQuery.setParameter(1, nationality);
//
//        return getAllQuery.getResultList();
    }

    public Player getPlayerByID(int id) {
        return entityManager.find(Player.class, id);
    }

    public Player insertOrUpdatePlayer(Player player) {
        Player result = entityManager.merge(player);

        logger.info("The Player [{}] was inserted/updated", result);
        return result;
    }

    public int deletePlayerById(int id) {

        int result = 0;

        Player player = entityManager.find(Player.class, id);
        if (player != null) {
            entityManager.remove(player);
            result = 1;
        }
        logger.info("deletePlayer with id [{}] from the db .. result is: {}", id, result);
        return result;
    }
}
