package vita.gamestats.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Team {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String discordName;

    private int numberOfGames;

    private String[] highestPick_Champions_names;

    private List<String> allPicked;

    private Float[] highestPick_Percentage;

    private String[] highestBan_Champions_names;

    private List<String> allBanned;

    private Float[] highestBan_Percentage;

    private String[] highestBannedAgainst_Champions_names;

    private List<String> allBannedAgainst;

    private Float[] highestBannedAgainst_Percentage;

    public Team(String discordName, String name) {
        this.discordName = discordName;
        this.name = name;
        this.numberOfGames = 0;
    }
}
