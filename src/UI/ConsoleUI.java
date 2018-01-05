package UI;

import java.util.Scanner;

public class ConsoleUI {

    private static Scanner sc = new Scanner(System.in);

    public static String readLine(){
        String line = sc.nextLine();

        if (line.equals("exit")){
            System.out.println("%n************************************* Goodbye ************************************");
            System.exit(0);
        }

        return line;
    }

    public static void print(Object o){
        System.out.printf(">> %s%n", o.toString());
    }

    public static void printf(Object o){
        System.out.printf(o.toString());
    }
}
