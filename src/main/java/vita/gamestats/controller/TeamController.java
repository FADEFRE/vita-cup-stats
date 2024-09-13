package vita.gamestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vita.gamestats.model.Team;
import vita.gamestats.service.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;
    

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String name) {
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @GetMapping("/{name}/id")
    public ResponseEntity<Long> getTeamIdByName(@PathVariable String name) {
        return ResponseEntity.ok(teamService.getTeamIdByName(name));
    }
}
