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
                askForPlayers();
                displayPlayers();
                askForRules();
                displayResult();
            }
            catch (Exception ex){
                print(ex.getMessage());
            }
        }
    }

    private static void initialize() {
        print("App is launched. Type \"exit\" to exit the app.");
        printf("****************** Started ******************%n%n");
    }

    public static void askForPlayers() throws EmptyNameException {
        print("Enter players separated by comma (e.g. Ben, John):");
        GameController.initializePlayers(readLine());
    }

    private static void displayPlayers() {
        printf("%n>> Created players: { ");
        for (Player p : PlayerFactory.getPlayers()) {
            printf(p + " ");
        }
        printf("}%n");
    }

    public static void askForRules() throws EmptyNameException {
        printf("%n>> All rules should be entered using the following syntax: \"{Ben /\\ ¬John} -> 5\".%n");
        print(">> Player names are case-insensitive (e.g \"Ben\" is the same as \"ben\").");
        print(">> Symbols allowed to represent conjunction: \"∧\", \"/\\\"(slash and backslash).");
        print(">> Allowed arrows: \"→\", \"=>\". \"->\".");
        print(">> Allowed negation symbols: \"¬\", \"˜\", \"!\".");
        print(">> Only one symbol of a particular type can be used.");
        print(">> Empty line indicates the end of the input.");
        print("Enter rules:");

        String line = null;

        while ((line = readLine()) != null && !line.isEmpty()){
            GameController.initializeRule(line);
        }
    }

    public static void displayResult(){
        //todo
        for (Player p : PlayerFactory.getPlayers()) {
            System.out.printf("%nPlayer %s:%n", p);
            for (Rule rule: p.getRules()){
                System.out.println(rule);
                System.out.println();
            }
        }
    }
}
