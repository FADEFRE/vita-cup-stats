package vita.gamestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vita.gamestats.dto.MatchDTO;
import vita.gamestats.model.Match;
import vita.gamestats.service.MatchService;



@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{team_one_id}/vs/{team_two_id}")
    public ResponseEntity<List<Match>> getAllMatchesFromTeams(@PathVariable Long team_one_id, @PathVariable Long team_two_id) {
        return ResponseEntity.ok(matchService.getAllMatchesFromTeams(team_one_id, team_two_id));
    }
    
    @GetMapping("/total")
    public ResponseEntity<Long> getTotalNumberOfMatches() {
        return ResponseEntity.ok(matchService.getTotalNumberOfMatches());
    }

    @PostMapping()
    public ResponseEntity<String> postMatch(@ModelAttribute MatchDTO submitDataDTO) {
        System.out.println("----------------------------------------------------------------------");
        return ResponseEntity.ok(matchService.saveMatch(submitDataDTO));
    }
    
    
}
