package vita.gamestats.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubmitDataDTO {
    private Long teamOne_id;
    private Long teamTwo_id;
    private String mvp;
    private int teamOne_Score;
    private int teamTwo_Score;
    private List<Long> teamOne_Pick_ids;
    private List<Long> teamOne_Ban_ids;
    private List<Long> teamTwo_Pick_ids;
    private List<Long> teamTwo_Ban_ids;
}
