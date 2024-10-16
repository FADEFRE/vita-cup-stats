package vita.gamestats.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vita.gamestats.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
    
    Boolean existsByDiscordName(String discordName);

    Optional<Team> findByDiscordName(String discordName);
    
    Optional<Team> findByName(String name);
}
