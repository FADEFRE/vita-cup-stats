package vita.gamestats.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialChampBanDTO {
    private String name;
    private int numberOfBans;
    private float banrate;
}
