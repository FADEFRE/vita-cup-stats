package vita.gamestats.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vita.gamestats.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    
    Optional<Player> findByName(String name);
}
