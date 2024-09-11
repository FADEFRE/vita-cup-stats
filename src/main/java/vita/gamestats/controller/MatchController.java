package vita.gamestats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vita.gamestats.dto.MatchDTO;
import vita.gamestats.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{team_id_one}/vs/{team_id_two}")
    public String getMethodName(@RequestParam String param) {
        return new String();
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
