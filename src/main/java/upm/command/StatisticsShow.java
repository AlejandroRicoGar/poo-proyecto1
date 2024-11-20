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
    /**
     * Applies the command
     * @param stringsep the parameters of the command "statistics-show -csv/-json"
     * @return the output of the command
     */
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if (stringsep.length == 2) {
            Player loggedPlayer = PublicController.getInstance().getPlayer(PublicController.getInstance().getLogged());
            if (stringsep[1].equals("-csv")) {
                output = PlayerController.getInstance().getStatisticsCSV(loggedPlayer);
            } else if (stringsep[1].equals("-json")) {
                output = PlayerController.getInstance().getStatisticsJSON(loggedPlayer);
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

