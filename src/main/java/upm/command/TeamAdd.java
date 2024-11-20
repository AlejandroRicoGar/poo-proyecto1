/**
 * Command to add a player to a team
 */
package upm.command;

import upm.controller.PlayerController;
import upm.controller.TeamController;

/**
 * Class to add a player to a team
 */
public class TeamAdd implements Command {

    /**
     * Team controller
     */
    private TeamController teamController;

    /**
     * Constructor with team controller and player controller as parameters
     * @param teamController Team controller
     */
    public TeamAdd(TeamController teamController) {
        this.teamController = teamController;
    }

    /**
     * Adds a player to a team
     * @param stringsep An array with the parameters "team-add PlayerName;TeamName"
     * @return The output of the command
     */
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        String[] params = stringsep[1].split(";");
        if(params.length == 2) {
            if (PlayerController.getInstance().search(params[0]) != null) {
                output = teamController.addPlayerToTeam(PlayerController.getInstance().search(params[0]), teamController.searchTeam(params[1]));
            } else {
                output = "The player does not exist";
            }
        } else {
            output = "Incorrect number of parameters";
        }
        return output;
    }

    /**
     * Returns a string with the command description
     * @return The command description
     */
    @Override
    public String toString() {
        return "> team-add PlayerName;TeamName";
    }

    /**
     * Returns the command name
     * @return The command name
     */
    @Override
    public String getCommand() {
        return "team-add";
    }
}

