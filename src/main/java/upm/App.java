package upm;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        CLI cli = new CLI();

        System.out.println("Run App..");
        Logic logic = new Logic(cli);
        logic.start();
    }
}
