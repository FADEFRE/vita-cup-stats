package vita.gamestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vita.gamestats.model.Champion;
import vita.gamestats.service.ChampionService;


@RestController
@RequestMapping("/api/champion")
public class ChampionController {

    @Autowired
    private ChampionService championService;

    @GetMapping
    public ResponseEntity<List<Champion>> getAllChampions() {
        return ResponseEntity.ok(championService.getAllChampions());
    }
    
    
}
