package vita.gamestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vita.gamestats.dto.MvpDTO;
import vita.gamestats.service.PlayerService;


@RestController
@RequestMapping("/api/player")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @GetMapping("/mvp/all")
    public ResponseEntity<List<MvpDTO>> getAllMvps() {
        return ResponseEntity.ok(playerService.getAllMvps());
    }
    
}
