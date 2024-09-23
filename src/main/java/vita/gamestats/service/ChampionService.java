package vita.gamestats.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import vita.gamestats.dto.ChampionPlayedInDTO;
import vita.gamestats.dto.ChampionStatsDTO;
import vita.gamestats.model.Champion;
import vita.gamestats.model.Match;
import vita.gamestats.repository.ChampionRepository;

@Service
public class ChampionService {
    
    @Autowired
    private ChampionRepository championRepository;

    private MatchService matchService;

    public ChampionService(@Lazy MatchService matchService) {
        this.matchService = matchService;
    }

    public List<Champion> getAllChampions() {
        return championRepository.findAll();
    }

    public List<String> getAllChampionNames() {
        List<Champion> allChampions = championRepository.findAll();
        List<String> allChampionNames = new ArrayList<>();
        for (Champion champ : allChampions) {
            allChampionNames.add(champ.getName());
        }
        return allChampionNames;
    }

    public Champion getChampionByName(String name) {
        return championRepository.findByName(name).orElseThrow();
    }

    public Champion getChampionById(Long id) {
        return championRepository.findById(id).orElseThrow();
    }

    public String getChampionNameById(Long id) {
        Champion champion = championRepository.findById(id).orElseThrow();
        return champion.getName();
    }

    public void updateData(
        long numberOfTotalGames, 
        List<String> team_1_picks, 
        List<String> team_1_bans, 
        int teamOne_Score,
        List<String> team_2_picks, 
        List<String> team_2_bans, 
        int teamTwo_Score) {

        if (teamOne_Score > teamTwo_Score) {
            addTotalPick(team_1_picks, true);
            addTotalPick(team_2_picks, false);
        } else {
            addTotalPick(team_1_picks, false);
            addTotalPick(team_2_picks, true);
        }
                
        addTotalBan(team_1_bans);
        addTotalBan(team_2_bans);

        List<String> allToEdit = getAllChampionNames();

        for (String string : allToEdit) {
            Champion champ = getChampionByName(string);
            System.out.println(string + ": " + champ.getTotalPickBan() + " / " + champ.getTotalPick() + " / " + numberOfTotalGames);
            champ.setAveragePickBan(((float)champ.getTotalPickBan()/numberOfTotalGames)*100);
            champ.setAveragePick(((float)champ.getTotalPick()/numberOfTotalGames)*100);
            championRepository.save(champ);
        }
    }


    private void addTotalPick(List<String> list, Boolean isWinner) {
        for (String string : list) {
            Champion champ = getChampionByName(string);
            champ.setTotalPick(champ.getTotalPick()+1);
            champ.setTotalPickBan(champ.getTotalPickBan()+1);
            if (isWinner) {
                champ.setWins(champ.getWins()+1);
            } else {
                champ.setLoss(champ.getLoss()+1);
            }
            championRepository.save(champ);
        }
    }

    private void addTotalBan(List<String> list) {
        for (String string : list) {
            Champion champ = getChampionByName(string);
            champ.setTotalPickBan(champ.getTotalPickBan()+1);
            championRepository.save(champ);
        }
    }

    public Champion[] getMostPickedChampions(int numberOf) {
        Champion[] returnChampions = new Champion[numberOf];
        List<Champion> allChampions = getAllChampions();
        for (Champion champion : allChampions) {
            if (champion.getTotalPick() == 0) { continue; }
            if (returnChampions[0] == null) { returnChampions[0] = champion; continue;}

            for (int i = 0; i < returnChampions.length; i++) {
                if (returnChampions[i].getTotalPick() > champion.getTotalPick()) {
                    if (i+1 > returnChampions.length-1) { break; }

                    if (returnChampions[i+1] == null) { returnChampions[i+1] = champion; break;}
                
                    if (returnChampions[i+1].getTotalPick() < champion.getTotalPick()) {
                        for (int j = returnChampions.length-1; j > i; j--) {
                            returnChampions[j] = returnChampions[j-1];
                        }
                        returnChampions[i+1] = champion;
                        break;
                    }

                } 
                else {
                    for (int j = returnChampions.length-1; j > i; j--) {
                        returnChampions[j] = returnChampions[j-1];
                    }
                    returnChampions[i] = champion;
                    break;
                }
            }
        }

        return returnChampions;
    }

    public Champion[] getMostBannedChampions(int numberOf) {
        Champion[] returnChampions = new Champion[numberOf];
        List<Champion> allChampions = getAllChampions();
        for (Champion champion : allChampions) {
            if (champion.getTotalPickBan() == 0) { continue; }
            if (returnChampions[0] == null) { returnChampions[0] = champion; continue;}

            for (int i = 0; i < returnChampions.length; i++) {
                int returnChampBan = returnChampions[i].getTotalPickBan() - returnChampions[i].getTotalPick();
                int champBan = champion.getTotalPickBan() - champion.getTotalPick();
                if (returnChampBan > champBan) {
                    if (i+1 > returnChampions.length-1) { break; }

                    if (returnChampions[i+1] == null) { returnChampions[i+1] = champion; break;}
                
                    int returnChampBan_1 = returnChampions[i+1].getTotalPickBan() - returnChampions[i+1].getTotalPick();
                    if (returnChampBan_1 < champBan) {
                        for (int j = returnChampions.length-1; j > i; j--) {
                            returnChampions[j] = returnChampions[j-1];
                        }
                        returnChampions[i+1] = champion;
                        break;
                    }

                } 
                else {
                    for (int j = returnChampions.length-1; j > i; j--) {
                        returnChampions[j] = returnChampions[j-1];
                    }
                    returnChampions[i] = champion;
                    break;
                }
            }
        }

        return returnChampions;
    }

    public Champion[] getHighestPresenceChampions(int numberOf) {
        Champion[] returnChampions = new Champion[numberOf];
        List<Champion> allChampions = getAllChampions();
        for (Champion champion : allChampions) {
            if (champion.getTotalPickBan() == 0) { continue; }
            if (returnChampions[0] == null) { returnChampions[0] = champion; continue;}

            for (int i = 0; i < returnChampions.length; i++) {
                if (returnChampions[i].getTotalPickBan() > champion.getTotalPickBan()) {
                    if (i+1 > returnChampions.length-1) { break; }

                    if (returnChampions[i+1] == null) { returnChampions[i+1] = champion; break;}
                
                    if (returnChampions[i+1].getTotalPickBan() < champion.getTotalPickBan()) {
                        for (int j = returnChampions.length-1; j > i; j--) {
                            returnChampions[j] = returnChampions[j-1];
                        }
                        returnChampions[i+1] = champion;
                        break;
                    }

                } 
                else {
                    for (int j = returnChampions.length-1; j > i; j--) {
                        returnChampions[j] = returnChampions[j-1];
                    }
                    returnChampions[i] = champion;
                    break;
                }
            }
        }

        return returnChampions;
    }

    public Champion[] getHighestWinrateChampionsWithoutFlawless(int numberOf) {
        Champion[] returnChampions = new Champion[numberOf];
        List<Champion> allChampions = getAllChampions();
        for (Champion champion : allChampions) {
            if (champion.getWins() == 0 || champion.getTotalPick() == 1 || champion.getLoss() == 0) { continue; }
            if (returnChampions[0] == null) { returnChampions[0] = champion; continue;}

            for (int i = 0; i < returnChampions.length; i++) {
                if (returnChampions[i].getWins()*100/returnChampions[i].getTotalPick() > champion.getWins()*100/champion.getTotalPick()) {
                    if (i+1 > returnChampions.length-1) { break; }

                    if (returnChampions[i+1] == null) { returnChampions[i+1] = champion; break;}
                
                    if (returnChampions[i+1].getWins()*100/returnChampions[i+1].getTotalPick() < champion.getWins()*100/champion.getTotalPick()) {
                        for (int j = returnChampions.length-1; j > i; j--) {
                            returnChampions[j] = returnChampions[j-1];
                        }
                        returnChampions[i+1] = champion;
                        break;
                    }

                } 
                else {
                    for (int j = returnChampions.length-1; j > i; j--) {
                        returnChampions[j] = returnChampions[j-1];
                    }
                    returnChampions[i] = champion;
                    break;
                }
            }
        }

        return returnChampions;
    }

    public Champion[] getHighestWinrateChampionsOnlyFlawless() {
        List<Champion> allChampions = getAllChampions();
        Champion[] firstArray = new Champion[allChampions.size()];
        for (Champion champion : allChampions) {
            if (champion.getWins() <= 1 || champion.getTotalPick() == 1 || champion.getLoss() > 0) { continue; }
            if (firstArray[0] == null) { firstArray[0] = champion; continue;}

            for (int i = 0; i < firstArray.length; i++) {
                if (firstArray[i].getWins() > champion.getWins()) {
                    if (i+1 > firstArray.length-1) { break; }

                    if (firstArray[i+1] == null) { firstArray[i+1] = champion; break;}
                
                    if (firstArray[i+1].getWins() < champion.getWins()) {
                        for (int j = firstArray.length-1; j > i; j--) {
                            firstArray[j] = firstArray[j-1];
                        }
                        firstArray[i+1] = champion;
                        break;
                    }

                } 
                else {
                    for (int j = firstArray.length-1; j > i; j--) {
                        firstArray[j] = firstArray[j-1];
                    }
                    firstArray[i] = champion;
                    break;
                }
            }
        }
        int lengthCounter = 0;
        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] == null) {
                break;
            }
            else {
                lengthCounter++;
            }
        }
        Champion[] returnChampions = new Champion[lengthCounter];
        for (int i = 0; i < returnChampions.length; i++) {
            returnChampions[i] = firstArray[i];
        }
        return returnChampions;
    }

    public Champion[] getLowestWinrateChampionsWithoutFlawless(int numberOf) {
        Champion[] returnChampions = new Champion[numberOf];
        List<Champion> allChampions = getAllChampions();
        for (Champion champion : allChampions) {
            if (champion.getLoss() == 0 || champion.getTotalPick() == 1 || champion.getWins() == 0) { continue; }
            if (returnChampions[0] == null) { returnChampions[0] = champion; continue;}

            for (int i = 0; i < returnChampions.length; i++) {
                if (returnChampions[i].getLoss()*100/returnChampions[i].getTotalPick() > champion.getLoss()*100/champion.getTotalPick()) {
                    if (i+1 > returnChampions.length-1) { break; }

                    if (returnChampions[i+1] == null) { returnChampions[i+1] = champion; break;}
                
                    if (returnChampions[i+1].getLoss()*100/returnChampions[i+1].getTotalPick() < champion.getLoss()*100/champion.getTotalPick()) {
                        for (int j = returnChampions.length-1; j > i; j--) {
                            returnChampions[j] = returnChampions[j-1];
                        }
                        returnChampions[i+1] = champion;
                        break;
                    }

                } 
                else {
                    for (int j = returnChampions.length-1; j > i; j--) {
                        returnChampions[j] = returnChampions[j-1];
                    }
                    returnChampions[i] = champion;
                    break;
                }
            }
        }

        return returnChampions;
    }

    public Champion[] getLowestWinrateChampionsOnlyFlawless() {
        List<Champion> allChampions = getAllChampions();
        Champion[] firstArray = new Champion[allChampions.size()];
        for (Champion champion : allChampions) {
            if (champion.getLoss() <= 1 || champion.getTotalPick() == 1 || champion.getWins() > 0) { continue; }
            if (firstArray[0] == null) { firstArray[0] = champion; continue;}

            for (int i = 0; i < firstArray.length; i++) {
                if (firstArray[i].getLoss() > champion.getLoss()) {
                    if (i+1 > firstArray.length-1) { break; }

                    if (firstArray[i+1] == null) { firstArray[i+1] = champion; break;}
                
                    if (firstArray[i+1].getLoss() < champion.getLoss()) {
                        for (int j = firstArray.length-1; j > i; j--) {
                            firstArray[j] = firstArray[j-1];
                        }
                        firstArray[i+1] = champion;
                        break;
                    }

                } 
                else {
                    for (int j = firstArray.length-1; j > i; j--) {
                        firstArray[j] = firstArray[j-1];
                    }
                    firstArray[i] = champion;
                    break;
                }
            }
        }
        int lengthCounter = 0;
        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] == null) {
                break;
            }
            else {
                lengthCounter++;
            }
        }
        Champion[] returnChampions = new Champion[lengthCounter];
        for (int i = 0; i < returnChampions.length; i++) {
            returnChampions[i] = firstArray[i];
        }
        return returnChampions;
    }


    public ChampionStatsDTO getChampionsMatches(String name, String option) {
        ChampionStatsDTO championStatsDTO = new ChampionStatsDTO();
        List<ChampionPlayedInDTO> list = new ArrayList<>();
        List<Match> allMatches = matchService.getAllMatches();
        List<Match> returnMatches = new ArrayList<>();

        Champion champion = getChampionByName(name);
        championStatsDTO.setChampion(champion);

        for (Match match : allMatches) {
            switch (option) {
                case "picked":
                    if (match.getTeam_1_pick_champion_names().contains(champion.getName()) || match.getTeam_2_pick_champion_names().contains(champion.getName())) {
                        returnMatches.add(match);
                    }
                    break;
                case "banned":
                    if (match.getTeam_1_ban_champion_names().contains(champion.getName()) || match.getTeam_2_ban_champion_names().contains(champion.getName())) {
                        returnMatches.add(match);
                    }
                    break;
                case "won":
                    if (
                        (match.getTeam_1_pick_champion_names().contains(champion.getName()) && match.getWinner() == 1L ) || 
                        (match.getTeam_2_pick_champion_names().contains(champion.getName()) && match.getWinner() == 2L ) 
                        ) {
                        returnMatches.add(match);
                    }
                    break;
                case "lost":
                    if (
                        (match.getTeam_1_pick_champion_names().contains(champion.getName()) && match.getWinner() == 2L ) || 
                        (match.getTeam_2_pick_champion_names().contains(champion.getName()) && match.getWinner() == 1L ) 
                        ) {
                        returnMatches.add(match);
                    }
                    break;
                default:
                    break;
            }
        }

        for (Match match : returnMatches) {
            ChampionPlayedInDTO dto = new ChampionPlayedInDTO();
            dto.setTeamOne(match.getTeam_1().getName());
            dto.setTeamTwo(match.getTeam_2().getName());
            if (match.getWinner() == 1) {
                dto.setWinner(match.getTeam_1().getName());
            } else {
                dto.setWinner(match.getTeam_2().getName());
            }
            list.add(dto);
        }

        championStatsDTO.setMatches(list);
        return championStatsDTO;
    }

    public Float getWinrateOfChampionOfTeam(String championName, Long teamId) {
        List<Match> teamMatches = matchService.getAllMatchesFromSingleTeam(teamId);
        List<Match> playedMatches = new ArrayList<>();
        int wins = 0;
        for (Match match : teamMatches) {
            if (match.getTeam_1_pick_champion_names().contains(championName) || match.getTeam_2_pick_champion_names().contains(championName)) {
                playedMatches.add(match);
            }
        }
        for (Match match : playedMatches) {
            if (match.getWinner() == 1L && match.getTeam_1_pick_champion_names().contains(championName)) {
                wins++;
            }
            else if (match.getWinner() == 2L && match.getTeam_2_pick_champion_names().contains(championName)) {
                wins++;
            }
        }

        Float returnValue = ((float) wins / playedMatches.size())*100;
        return returnValue;
    }

}
