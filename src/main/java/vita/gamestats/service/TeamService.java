package vita.gamestats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vita.gamestats.model.Team;
import vita.gamestats.repository.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamByName(String name) {
        return teamRepository.findByName(name).orElseThrow();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow();
    }

    public String getTeamNameById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow();
        return team.getName();
    }
    
}
