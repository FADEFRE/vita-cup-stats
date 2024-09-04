package vita.gamestats.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity

@Data
public class Match {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private StreamEnum stream;

    private Boolean isActive;

    @ManyToOne
    private Team team_1;

    @ManyToOne
    private Team team_2;

    private List<String> team_1_pick_champion_names;

    private List<String> team_1_ban_champion_names;

    private List<String> team_2_pick_champion_names;

    private List<String> team_2_ban_champion_names;
}
