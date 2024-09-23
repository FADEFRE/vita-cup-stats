package vita.gamestats.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChampionPlayedInDTO {
    private String teamOne;
    private String teamTwo;
    private String winner;
}
