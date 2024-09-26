package vita.gamestats.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import vita.gamestats.views.SocialChampView;

@Entity
@Data
@NoArgsConstructor
public class Champion {
    
    @Id
    @GeneratedValue
    private Long id;

    @JsonView({
        SocialChampView.SocialChampPresenceView.class, 
        SocialChampView.SocialChampWinrateView.class, 
        SocialChampView.SocialChampLossrateView.class, 
        SocialChampView.SocialChampPickrateView.class, 
        SocialChampView.SocialChampBanrateView.class
    })
    private String name;

    @JsonView({
        SocialChampView.SocialChampPresenceView.class,
    })
    private Float averagePickBan;

    @JsonView({
        SocialChampView.SocialChampPresenceView.class,
    })
    private int totalPickBan;

    @JsonView({
        SocialChampView.SocialChampPickrateView.class,
    })
    private Float averagePick;

    @JsonView({
        SocialChampView.SocialChampPickrateView.class,
    })
    private int totalPick;

    @JsonView({
        SocialChampView.SocialChampWinrateView.class,
        SocialChampView.SocialChampLossrateView.class,
    })
    private int wins;

    @JsonView({
        SocialChampView.SocialChampWinrateView.class,
        SocialChampView.SocialChampLossrateView.class,
    })
    private int loss;

    public Champion(String name) {
        this.name = name;
        this.totalPick = 0;
        this.totalPickBan = 0;
        this.wins = 0;
        this.loss = 0;
    }
}
