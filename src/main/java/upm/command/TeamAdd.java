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
     * Player controller
     */
    private PlayerController playerController;

    /**
     * Constructor with team controller and player controller as parameters
     * @param teamController Team controller
     * @param playerController Player controller
     */
    public TeamAdd(TeamController teamController, PlayerController playerController) {
        this.teamController = teamController;
        this.playerController = playerController;
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
            if (playerController.search(params[0]) != null) {
                output = teamController.addPlayerToTeam(playerController.search(params[0]), teamController.searchTeam(params[1]));
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

