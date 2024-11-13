package upm.command;

import upm.controller.PlayerController;
import upm.controller.PublicController;
import upm.model.Player;

public class StatisticsShow implements Command{
    private PlayerController playerController;
    private PublicController publicController;

    public StatisticsShow(PlayerController playerController, PublicController publicController) {
        this.playerController = playerController;
        this.publicController = publicController;
    }
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if (stringsep.length == 2) {
            Player loggedPlayer = publicController.getPlayer(publicController.getLogged());
            if (stringsep[1].equals("-csv")) {
                output = playerController.getStatisticsCSV(loggedPlayer);
            } else if (stringsep[1].equals("-json")) {
                output = playerController.getStatisticsJSON(loggedPlayer);
            } else {
                output = "Invalid option";
            }
        } else {
            output = "Incorrect number of parameters";
        }
        return output;

    }

    @Override
    public String toString() {
        return "> statistics-show -csv/-json";
    }

    @Override
    public String getCommand() {
        return "statistics-show";
    }
}
