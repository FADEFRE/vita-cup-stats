package vita.gamestats.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import vita.gamestats.dto.MatchDTO;
import vita.gamestats.model.Match;
import vita.gamestats.model.Team;
import vita.gamestats.repository.MatchRepository;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    private TeamService teamService;
    private ChampionService championService;
    private PlayerService playerService;

    public MatchService(@Lazy TeamService teamService, @Lazy ChampionService championService, @Lazy PlayerService playerService) {
        this.teamService = teamService;
        this.championService = championService;
        this.playerService = playerService;
    }

    public Long getTotalNumberOfMatches() {
        return matchRepository.count();
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public String saveMatch(MatchDTO submitDataDTO) {
        Match match = new Match();

        match.setDate(LocalDate.now());

        Team team_1 = teamService.getTeamById(submitDataDTO.getTeamOne_id());
        match.setTeam_1(team_1);
        Team team_2 = teamService.getTeamById(submitDataDTO.getTeamTwo_id());
        match.setTeam_2(team_2);

        match.setName(team_1.getName() + " vs. " + team_2.getName());

        String mvpName = submitDataDTO.getMvp();
        match.setMvp(mvpName);
        playerService.addMvp(mvpName);

        if (submitDataDTO.getTeamOne_Score() == 1) {
            match.setWinner(1L);
        } 
        if (submitDataDTO.getTeamTwo_Score() == 1) {
            match.setWinner(2L);
        }

        List<String> team_1_picks = convertIdsListToNamesList(submitDataDTO.getTeamOne_Pick_ids());
        List<String> team_1_bans = convertIdsListToNamesList(submitDataDTO.getTeamOne_Ban_ids());
        List<String> team_2_picks = convertIdsListToNamesList(submitDataDTO.getTeamTwo_Pick_ids());
        List<String> team_2_bans = convertIdsListToNamesList(submitDataDTO.getTeamTwo_Ban_ids());
        match.setTeam_1_pick_champion_names(team_1_picks);
        match.setTeam_1_ban_champion_names(team_1_bans);
        match.setTeam_2_pick_champion_names(team_2_picks);
        match.setTeam_2_ban_champion_names(team_2_bans);

        teamService.updateData(team_1, team_1_picks, team_1_bans, team_2_bans);
        teamService.updateData(team_2, team_2_picks, team_2_bans, team_1_bans);

        matchRepository.save(match);

        championService.updateData(matchRepository.count(), team_1_picks, team_1_bans, submitDataDTO.getTeamOne_Score(), team_2_picks, team_2_bans, submitDataDTO.getTeamTwo_Score());
        return "saved";
    }
    
    public List<Match> getAllMatchesFromTeams(Long team_one_id, Long team_two_id) {
        List<Match> allTeamOneMatches = getAllMatchesFromSingleTeam(team_one_id);
        List<Match> allCombinedMatches = new ArrayList<>();

        for (Match match : allTeamOneMatches) {
            if (match.getTeam_1().getId() == team_two_id || match.getTeam_2().getId() == team_two_id) {
                allCombinedMatches.add(match);
            }
        }
        return allCombinedMatches;
    }

    public List<Match> getAllMatchesFromSingleTeam(Long team_one_id) {
        List<Match> allMatches = matchRepository.findAll();
        List<Match> allTeamOneMatches = new ArrayList<>();
        for (Match match : allMatches) {
            if (match.getTeam_1().getId() == team_one_id || match.getTeam_2().getId() == team_one_id) {
                allTeamOneMatches.add(match);
            }
        }
        return allTeamOneMatches;
    }

    private List<String> convertIdsListToNamesList(List<Long> idList) {
        List<String> stringList = new ArrayList<>();

        for (Long id : idList) {
            String name = championService.getChampionNameById(id);
            stringList.add(name);
        }

        return stringList;
    }
}
