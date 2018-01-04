package game.model;

import game.exceptions.EmptyNameException;
import java.util.*;
import java.util.regex.Pattern;

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

    public static Map<Boolean, List<Player>> parsePlayers(String line) throws EmptyNameException {
        if (line.contains("{") && line.contains("}")){
            line = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        }

        Map<Boolean, List<Player>> players = new HashMap<>();
        players.put(true, new ArrayList<>());
        players.put(false, new ArrayList<>());

        String[] names = line.replace(" ", "").split("∧|/\\\\|,");
        for (String name : names) {
            boolean isPositive = !isNegated(name);
            name = isPositive ? name : name.substring(1);
            players.get(isPositive).add(getPlayer(name));
        }

        return Collections.unmodifiableMap(players);
    }

    private static boolean isNegated(String playerName){
        return playerName.matches("^(¬|-|!)[a-zA-Z0-9_]*");
    }

    public static void refresh(){
        players = new ArrayList<>();
    }
}
