package vita.gamestats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vita.gamestats.model.Team;

@Repository
public interface TeamRepositroy extends JpaRepository<Team, Long>{
    
    Boolean existsByDiscordName(String discordName);
}
