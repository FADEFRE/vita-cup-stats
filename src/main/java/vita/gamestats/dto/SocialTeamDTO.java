package vita.gamestats.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialTeamDTO {
    private String name;
    private Float winrate;
    private int numberOfGames;
    private int numberOfWins;
    private int numberOfLoss;
    private int numberOfAllPicks;
}
