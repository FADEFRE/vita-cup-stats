package vita.gamestats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Champion {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Float averagePickBan;

    private int totalPickBan;

    private Float averagePick;

    private int totalPick;

    private int wins;

    private int loss;

    public Champion(String name) {
        this.name = name;
        this.totalPick = 0;
        this.totalPickBan = 0;
        this.wins = 0;
        this.loss = 0;
    }
}
