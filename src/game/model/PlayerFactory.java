package game.model;

import game.exceptions.EmptyNameException;
import game.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerFactory {

    private static List<Player> players = new ArrayList<>();


    public static Player getPlayer(String name) throws EmptyNameException {
        
        if (name == null || name.isEmpty()) {
            throw new EmptyNameException();
        }

        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }

        Player newPlayer = new Player(name);
        players.add(newPlayer);

        return newPlayer;
    }

    public static List<Player> getPlayers(){
        return Collections.unmodifiableList(players);
    }
}
