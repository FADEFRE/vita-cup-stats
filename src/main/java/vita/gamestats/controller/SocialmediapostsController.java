package vita.gamestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import vita.gamestats.dto.SocialChampBanDTO;
import vita.gamestats.dto.SocialChampDTO;
import vita.gamestats.dto.SocialTeamDTO;
import vita.gamestats.model.Champion;
import vita.gamestats.service.ChampionService;
import vita.gamestats.service.TeamService;
import vita.gamestats.views.SocialChampView;



@RestController
@RequestMapping("/api/social")
public class SocialmediapostsController {

    @Autowired
    private ChampionService championService;
    @Autowired
    private TeamService teamService;
    
    @GetMapping("/picked")
    @JsonView(SocialChampView.SocialChampPickrateView.class)
    public ResponseEntity<Champion[]> getMostPickedChampions() {
        return ResponseEntity.ok(championService.getMostPickedChampions(12));
    }

    @GetMapping("/banned")
    public ResponseEntity<SocialChampBanDTO[]> getSocialChampBanStats() {
        return ResponseEntity.ok(championService.getSocialChampBanStats(12));
    }

    @GetMapping("/presence")
    @JsonView(SocialChampView.SocialChampPresenceView.class)
    public ResponseEntity<Champion[]> getHighestPresenceChampions() {
        return ResponseEntity.ok(championService.getHighestPresenceChampions(12));
    }

    @GetMapping("/winrate")
    @JsonView(SocialChampView.SocialChampWinrateView.class)
    public ResponseEntity<Champion[]> getSocialHighestWinrateChamps() {
        return ResponseEntity.ok(championService.getSocialHighestWinrateChamps(12));
    }

    @GetMapping("/winrate/flawless")
    public ResponseEntity<Champion[]> getHighestWinrateChampionsFlawless() {
        return ResponseEntity.ok(championService.getHighestWinrateChampionsOnlyFlawless());
    }

    @GetMapping("/lossrate")
    @JsonView(SocialChampView.SocialChampLossrateView.class)
    public ResponseEntity<Champion[]> getSocialLowestWinrateChamps() {
        return ResponseEntity.ok(championService.getSocialLowestWinrateChamps(12));
    }

    @GetMapping("/lossrate/flawless")
    public ResponseEntity<Champion[]> getLowestWinrateChampionsFlawless() {
        return ResponseEntity.ok(championService.getLowestWinrateChampionsOnlyFlawless());
    }

    @GetMapping("/team/all")
    public ResponseEntity<List<SocialTeamDTO>> getAllTeamSocialStats() {
        return ResponseEntity.ok(teamService.getAllTeamSocialStats());
    }

    @GetMapping("/champ/all")
    public ResponseEntity<List<SocialChampDTO>> getAllChampSocialStats() {
        return ResponseEntity.ok(championService.getAllChampSocialStats());
    }

    @GetMapping("/team/{teamName}/most-banned")
    public ResponseEntity<SocialChampDTO[]> getMostBannedChampionsOfTeam(@PathVariable String teamName) {
        return ResponseEntity.ok(championService.getMostBannedChampionsOfTeam(teamName));
    }

}
