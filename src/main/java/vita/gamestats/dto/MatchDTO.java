package vita.gamestats.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vita.gamestats.model.Match;

@Getter
@Setter
@NoArgsConstructor
public class MatchDTO {
    private Long teamOne_id;
    private Long teamTwo_id;
    private String teamOne_name;
    private String teamTwo_name;
    private String mvp;
    private int teamOne_Score;
    private int teamTwo_Score;
    private List<Long> teamOne_Pick_ids;
    private List<Long> teamOne_Ban_ids;
    private List<Long> teamTwo_Pick_ids;
    private List<Long> teamTwo_Ban_ids;
    private LocalDate date;


    public MatchDTO(Match match) {
        this.teamOne_name = match.getTeam_1().getName();
        this.teamTwo_name = match.getTeam_2().getName();
        this.mvp = match.getMvp();

        this.date = match.getDate();
        this.date = match.getDate();
        this.date = match.getDate();
        this.date = match.getDate();
    }
}
