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

    private Float averagePick;

    private Long wins;

    private Long loss;

    public Champion(String name) {
        this.name = name;
    }
}
