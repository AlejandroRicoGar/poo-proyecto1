/**
 * Command to show the statistics of a player
 */
package upm.command;

import upm.controller.PlayerController;
import upm.controller.PublicController;
import upm.model.Player;

/**
 * Class to show the statistics of a player
 */
public class StatisticsShow implements Command{
    private PlayerController playerController;
    private PublicController publicController;

    /**
     * Constructor of the class
     * @param playerController the player controller
     * @param publicController the public controller
     */
    public StatisticsShow(PlayerController playerController, PublicController publicController) {
        this.playerController = playerController;
        this.publicController = publicController;
    }

    /**
     * Applies the command
     * @param stringsep the parameters of the command "statistics-show -csv/-json"
     * @return the output of the command
     */
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

    /**
     * Returns a string with the command description
     * @return the command description
     */
    @Override
    public String toString() {
        return "> statistics-show -csv/-json";
    }

    /**
     * Returns the command name
     * @return the command name
     */
    @Override
    public String getCommand() {
        return "statistics-show";
    }
}

