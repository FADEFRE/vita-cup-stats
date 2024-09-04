package vita.gamestats.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Team {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String discordName;

    private String[] highestPick_Champions_names;

    private Float[] highestPick_Percentage;

    private String[] highestBan_Champions_names;

    private Float[] highestBan_Percentage;

    private String[] highestBannedAgainst_Champions_names;

    private Float[] highestBannedAgainst_Percentage;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public Team(String discordName, String name) {
        this.discordName = discordName;
        this.name = name;
    }
}
