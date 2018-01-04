import game.GameController;
import game.exceptions.EmptyNameException;
import game.model.Player;
import game.model.PlayerFactory;
import game.model.Rule;

import java.util.ArrayList;
import java.util.List;
import static UI.ConsoleUI.print;
import static UI.ConsoleUI.printf;
import static UI.ConsoleUI.readLine;


public class App {

    public static void main(String[] args) {
        initialize();
        while (true){
            try {
                askForRules();
                displayRulesPerPlayers();
            }
            catch (Exception ex){
                print(ex.getMessage());
            }
        }
    }

    private static void initialize() {
        printf("************************************* Started ************************************%n%n");
        printf(">> All rules should be entered using the following syntax: \"{Ben /\\ ¬John} -> 5\".%n");
        print("Player names are case-insensitive (e.g \"Ben\" is the same as \"ben\").");
        print("Symbols allowed to represent conjunction: \"∧\", \"/\\\"(slash and backslash).");
        print("Allowed arrows: \"→\", \"=>\". \"->\".");
        print("Allowed negation symbols: \"¬\", \"-\", \"!\".");
        print("Only one symbol of a particular type can be used.");
        print("Empty line indicates the end of the input.");
        printf(">> Type \"exit\" to exit the app.%n%n");
    }

    private static void displayPlayers() {
        print("Created players: { ");
        for (Player p : PlayerFactory.getPlayers()) {
            printf(p + " ");
        }
        printf("}%n");
    }

    public static void askForRules() throws EmptyNameException {
        printf("Enter rules:%n");

        String line = null;

        while ((line = readLine()) != null && !line.isEmpty()){
            GameController.initializeRule(line);
        }
    }



    public static void displayRulesPerPlayers(){
        //todo
        for (Player p : PlayerFactory.getPlayers()) {
            System.out.printf("Player %s:%n", p);
            for (Rule rule: p.getRules()){
                System.out.println(rule);
            }
            System.out.println();
        }

        GameController.calculateShapley();
    }

}
