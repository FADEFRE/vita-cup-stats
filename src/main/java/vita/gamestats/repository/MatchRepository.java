package vita.gamestats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vita.gamestats.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
    
}
