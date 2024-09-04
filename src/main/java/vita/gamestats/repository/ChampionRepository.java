package vita.gamestats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vita.gamestats.model.Champion;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long>{
    
    Boolean existsByName(String name);
}
