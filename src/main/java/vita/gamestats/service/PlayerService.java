package vita.gamestats.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vita.gamestats.model.Player;
import vita.gamestats.repository.PlayerRepository;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;

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
}
