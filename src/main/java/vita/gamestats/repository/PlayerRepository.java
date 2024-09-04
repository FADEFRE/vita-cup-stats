package vita.gamestats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vita.gamestats.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
