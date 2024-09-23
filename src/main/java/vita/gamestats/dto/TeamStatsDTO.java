package vita.gamestats.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import vita.gamestats.model.Team;

@Data
@NoArgsConstructor
public class TeamStatsDTO {
    private String name;
    private String discordName;
    private int numberOfGames;
    private String[] highestPick_Champions_names;
    private Float[] highestPick_Percentage;
    private Float[] highestPick_Winrate;
    private String[] highestBan_Champions_names;
    private Float[] highestBan_Percentage;
    private String[] highestBannedAgainst_Champions_names;
    private Float[] highestBannedAgainst_Percentage;

    public TeamStatsDTO(Team team) {
        this.name = team.getName();
        this.discordName = team.getDiscordName();
        this.numberOfGames = team.getNumberOfGames();
        this.highestPick_Champions_names = team.getHighestPick_Champions_names();
        this.highestPick_Percentage = team.getHighestPick_Percentage();
        this.highestBan_Champions_names = team.getHighestPick_Champions_names();
        this.highestBan_Percentage = team.getHighestBan_Percentage();
        this.highestBannedAgainst_Champions_names = team.getHighestBannedAgainst_Champions_names();
        this.highestBannedAgainst_Percentage = team.getHighestBannedAgainst_Percentage();
    }
}
