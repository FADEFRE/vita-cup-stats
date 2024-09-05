package vita.gamestats;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import vita.gamestats.model.Champion;
import vita.gamestats.model.Team;
import vita.gamestats.repository.ChampionRepository;
import vita.gamestats.repository.TeamRepository;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private ChampionRepository championRepository;

    @Autowired
    private TeamRepository teamRepositroy;

    private final ObjectMapper objectMapper;

    private String championsFile = "/champions.json";
    private String teamsData = "/teams.json";


    public DataLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        createChampions();
        createTeams();
    }
    

    private void createChampions() {
        JsonNode champions = grabFirstNodeFromJson("champions", championsFile);
        for (JsonNode jsonNode : champions) {
            String name = jsonNode.asText();
            if (!championRepository.existsByName(name)) {
                Champion champ = new Champion(name);
                championRepository.save(champ);
            }
        }
    }

    private void createTeams() {
        JsonNode teams = grabFirstNodeFromJson("teams", teamsData);
        for (JsonNode jsonNode : teams) {
            String discordName = jsonNode.asText();
            if (!teamRepositroy.existsByDiscordName(discordName)) {
                String[] nameArray = discordName.split("] ", 2);
                String name = nameArray[1];
                Team team = new Team(discordName, name);
                teamRepositroy.save(team);
            }
        }
    }

    private JsonNode grabFirstNodeFromJson(String nodeName, String filename) {
        JsonNode jsonNode;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream(filename)) {
            jsonNode = objectMapper.readValue(inputStream, JsonNode.class);
        } 
        catch (IOException e) { throw new RuntimeException("Failed to read JSON data", e); }

        return Optional.ofNullable(jsonNode)
                .map(j -> j.get(nodeName))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }
}
