package vita.gamestats.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vita.gamestats.dto.MvpDTO;
import vita.gamestats.model.Player;
import vita.gamestats.repository.PlayerRepository;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayer(String name) {
        return playerRepository.findByName(name).orElseThrow();
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player createOrFindPlayerByName(String name) {
        Optional<Player> dbPlayer = playerRepository.findByName(name);
        if (dbPlayer.isPresent()) {
            return dbPlayer.get();
        }
        else {
            Player newPlayer = new Player(name);
            return playerRepository.save(newPlayer);
        }
    }

    public void addMvp(String name) {
        Player mvpPlayer = createOrFindPlayerByName(name);
        int newCount = mvpPlayer.getMvpCount() + 1;
        mvpPlayer.setMvpCount(newCount);
        playerRepository.save(mvpPlayer);
    }

    public List<MvpDTO> getAllMvps() {
        List<MvpDTO> list = new ArrayList<>();
        List<Player> allPlayers = getAllPlayers();
        for (Player player : allPlayers) {
            if (player.getMvpCount() > 0) {
                MvpDTO dto = new MvpDTO();
                dto.setName(player.getName());
                dto.setNumber(player.getMvpCount());
                list.add(dto);
            }
        }

        return list;
    }

}
