package vita.gamestats.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vita.gamestats.model.Champion;
import vita.gamestats.repository.ChampionRepository;

@Service
public class ChampionService {
    
    @Autowired
    private ChampionRepository championRepository;

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
}
