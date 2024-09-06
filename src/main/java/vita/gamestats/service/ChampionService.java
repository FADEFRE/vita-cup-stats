package vita.gamestats.service;

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

}
