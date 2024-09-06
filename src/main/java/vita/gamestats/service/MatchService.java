package vita.gamestats.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import vita.gamestats.dto.SubmitDataDTO;
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

    public String saveMatch(SubmitDataDTO submitDataDTO) {
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

        match.setTeam_1_pick_champion_names(convertIdsListToNamesList(submitDataDTO.getTeamOne_Pick_ids()));
        match.setTeam_1_ban_champion_names(convertIdsListToNamesList(submitDataDTO.getTeamOne_Ban_ids()));
        match.setTeam_2_pick_champion_names(convertIdsListToNamesList(submitDataDTO.getTeamTwo_Pick_ids()));
        match.setTeam_2_ban_champion_names(convertIdsListToNamesList(submitDataDTO.getTeamTwo_Ban_ids()));


        matchRepository.save(match);
        return "saved";
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
