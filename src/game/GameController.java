package game;

import UI.ConsoleUI;
import game.exceptions.EmptyNameException;
import game.model.Player;
import game.model.PlayerFactory;
import game.model.Rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    public static void initializeRule(String line) throws EmptyNameException {
        Map<Boolean, List<Player>> literals = PlayerFactory.parsePlayers(line);
        Rule rule = new Rule(line, literals.get(true), literals.get(false), Rule.parseValue(line));

        List<Player> allPlayers = new ArrayList<>(literals.get(true));
        allPlayers.addAll(literals.get(false));

        addRuleToPlayers(rule, allPlayers);
    }

    private static void addRuleToPlayers(Rule rule, List<Player> players) {
        for (Player p : players) {
            p.addRule(rule);
        }

    }

    public static void calculateShapley(){
        Map<Player, BigDecimal> shapley = new HashMap<>();

        for (Player p : PlayerFactory.getPlayers()) {
            shapley.put(p, p.calculateShapley());
        }

        ConsoleUI.printf(shapley);
        PlayerFactory.refresh();
    }
}
