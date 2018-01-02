package game;

import game.exceptions.EmptyNameException;
import game.model.Player;
import game.model.PlayerFactory;
import game.model.Rule;

import java.math.BigInteger;

public class GameController {

    public static void initializePlayers(String line) throws EmptyNameException {
        for (String name : Player.parsePlayersNames(line)) {
            PlayerFactory.getPlayer(name);
        }
    }

    public static void initializeRule(String line) throws EmptyNameException {
        String[] playersNames = Player.parsePlayersNames(line);
        BigInteger playersInRule = new BigInteger(Integer.toString(playersNames.length));
        Rule rule = new Rule(line, playersInRule, Rule.parseValue(line));
        mapRuleToPlayers(rule, playersNames);
    }

    private static void mapRuleToPlayers(Rule rule, String[] pNames) throws EmptyNameException {
        for (String name : pNames) {
            Player p = PlayerFactory.getPlayer(name);
            p.addRule(rule);
        }
    }
}
