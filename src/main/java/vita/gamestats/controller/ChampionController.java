package vita.gamestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{name}")
    public ResponseEntity<Champion> getChampionByName(@PathVariable String name) {
        return ResponseEntity.ok(championService.getChampionByName(name));
    }
    
    @GetMapping("/picked")
    public ResponseEntity<Champion[]> getMostPickedChampions() {
        return ResponseEntity.ok(championService.getMostPickedChampions(5));
    }

    @GetMapping("/banned")
    public ResponseEntity<Champion[]> getMostBannedChampions() {
        return ResponseEntity.ok(championService.getMostBannedChampions(5));
    }

    @GetMapping("/presence")
    public ResponseEntity<Champion[]> getHighestPresenceChampions() {
        return ResponseEntity.ok(championService.getHighestPresenceChampions(5));
    }

    @GetMapping("/winrate")
    public ResponseEntity<Champion[]> getHighestWinrateChampions() {
        return ResponseEntity.ok(championService.getHighestWinrateChampionsWithoutFlawless(5));
    }

    @GetMapping("/winrate/flawless")
    public ResponseEntity<Champion[]> getHighestWinrateChampionsFlawless() {
        return ResponseEntity.ok(championService.getHighestWinrateChampionsOnlyFlawless());
    }

    @GetMapping("/lossrate")
    public ResponseEntity<Champion[]> getLowestWinrateChampions() {
        return ResponseEntity.ok(championService.getLowestWinrateChampionsWithoutFlawless(5));
    }

    @GetMapping("/lossrate/flawless")
    public ResponseEntity<Champion[]> getLowestWinrateChampionsFlawless() {
        return ResponseEntity.ok(championService.getLowestWinrateChampionsOnlyFlawless());
    }

}
