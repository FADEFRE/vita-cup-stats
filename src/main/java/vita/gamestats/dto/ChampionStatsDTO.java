package vita.gamestats.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import vita.gamestats.model.Champion;

@Data
@NoArgsConstructor
public class ChampionStatsDTO {
    private Champion champion;
    private List<ChampionMatchupStatsDTO> championMatchupStatsDTOs;
    private List<ChampionPlayedInDTO> matches;
}
