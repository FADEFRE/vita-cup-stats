package vita.gamestats.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Player {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String inGameName;

    @ManyToOne
    @JoinColumn(name = "Team_ID")
    private Team team;

    private String[] highestPick_Champions_name;

    private Float[] highestPick_Percentage;
}
