package vita.gamestats.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Long getTeamIdByName(String name) {
        Team team = getTeamByName(name);
        return team.getId();
    }

    public void updateData(Team team, List<String> team_picks, List<String> team_bans, List<String> enemy_bans) {
        team.setNumberOfGames(team.getNumberOfGames()+1);

        String[] highestPickedArray = team.getHighestPick_Champions_names();
        if (highestPickedArray == null || highestPickedArray.length == 0) {
            team.setAllPicked(team_picks);
            String[] newArray = new String[5];
            for (int i = 0; i < team_picks.size(); i++) {
                newArray[i] = team_picks.get(i);
            }
            team.setHighestPick_Champions_names(newArray);
        }
        else {
            List<String> allPicked = team.getAllPicked();
            allPicked.addAll(team_picks);
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String string : allPicked) {
                frequencyMap.put(string, frequencyMap.getOrDefault(string, 0) + 1);
            }
            Map<String, Integer> sorted = frequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            String[] onlyFiveArray = getSortedMap(sorted);
            team.setHighestPick_Champions_names(onlyFiveArray);

            team.setHighestPick_Percentage(getPercentageArray(onlyFiveArray, sorted, team.getNumberOfGames()));
        }


        String[] highestBannedArray = team.getHighestBan_Champions_names();
        if (highestBannedArray == null || highestBannedArray.length == 0) {
            team.setAllBanned(team_bans);
            String[] newArray = new String[5];
            for (int i = 0; i < team_bans.size(); i++) {
                newArray[i] = team_bans.get(i);
            }
            team.setHighestBan_Champions_names(newArray);
        }
        else {
            List<String> allBanned = team.getAllBanned();
            allBanned.addAll(team_bans);
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String string : allBanned) {
                frequencyMap.put(string, frequencyMap.getOrDefault(string, 0) + 1);
            }
            Map<String, Integer> sorted = frequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            String[] onlyFiveArray = getSortedMap(sorted);
            team.setHighestBan_Champions_names(onlyFiveArray);

            team.setHighestBan_Percentage(getPercentageArray(onlyFiveArray, sorted, team.getNumberOfGames()));
        }


        String[] highestBannedAgainstArray = team.getHighestBannedAgainst_Champions_names();
        if (highestBannedAgainstArray == null || highestBannedAgainstArray.length == 0) {
            team.setAllBannedAgainst(enemy_bans);
            String[] newArray = new String[5];
            for (int i = 0; i < enemy_bans.size(); i++) {
                newArray[i] = enemy_bans.get(i);
            }
            team.setHighestBannedAgainst_Champions_names(newArray);
        }
        else {
            List<String> allBannedAgainst = team.getAllBannedAgainst();
            allBannedAgainst.addAll(enemy_bans);
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String string : allBannedAgainst) {
                frequencyMap.put(string, frequencyMap.getOrDefault(string, 0) + 1);
            }
            Map<String, Integer> sorted = frequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            String[] onlyFiveArray = getSortedMap(sorted);
            team.setHighestBannedAgainst_Champions_names(onlyFiveArray);

            team.setHighestBannedAgainst_Percentage(getPercentageArray(onlyFiveArray, sorted, team.getNumberOfGames()));
        }

        teamRepository.save(team);
    }



    private String[] getSortedMap(Map<String, Integer> frequencyMap) {
        Map<String, Integer> newMap = new HashMap<>(frequencyMap);
        for (String key : frequencyMap.keySet()) {
            if (newMap.size() <= 5) {
                break;
            }
            newMap.remove(key);
        }
        Set<String> keys = newMap.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (frequencyMap.get(array[i]) < frequencyMap.get(array[j])) {
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp; 
                }
            }
        }
        return array;
    }

    private Float[] getPercentageArray(String[] onlyFiveArray, Map<String, Integer> sorted, int numberOfGames) {
        Float[] percentagFloats = new Float[onlyFiveArray.length];
        for (int i = 0; i < onlyFiveArray.length; i++) {
            float f = ((float) sorted.get(onlyFiveArray[i]) / numberOfGames)*100;
            percentagFloats[i] = f;
        }
        return percentagFloats;
    }


}
