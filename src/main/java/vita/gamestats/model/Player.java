package vita.gamestats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Player {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String inGameName;

    private int mvpCount;

    public Player(String name) {
        this.name = name;
        this.mvpCount = 0;
    }

}
