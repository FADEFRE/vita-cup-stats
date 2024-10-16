package vita.gamestats.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialChampDTO {
    private String name;

    private int numberOfPicks;
    private Float pickrate;

    private int numberOfBans;
    private Float banrate;

    private int numberOfPresences;
    private Float presenceRate;

    private int numberOfWins;
    private Float winrate;

    private int numberOfLosses;
    private Float lossrate;
}
