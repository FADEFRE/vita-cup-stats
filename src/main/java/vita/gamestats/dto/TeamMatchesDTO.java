package vita.gamestats.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamMatchesDTO {
    private String name;
    private Long winner;
    private String team_one;
    private String team_two;    
    private List<String> team_1_pick_champion_names;
    private List<String> team_1_ban_champion_names;
    private List<String> team_2_pick_champion_names;
    private List<String> team_2_ban_champion_names;
    
}
