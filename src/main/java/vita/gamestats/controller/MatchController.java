package vita.gamestats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vita.gamestats.dto.SubmitDataDTO;
import vita.gamestats.service.MatchService;


@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping()
    public ResponseEntity<String> postMatch(@ModelAttribute SubmitDataDTO submitDataDTO) {
        System.out.println("hallo");
        return ResponseEntity.ok(matchService.saveMatch(submitDataDTO));
    }
    
    
}
