/**
 * Command to delete a player from the system
 */
package upm.command;

import upm.controller.PlayerController;
import upm.controller.TeamController;
import upm.model.Team;

/**
 * Class to delete a player from the system
 */
public class PlayerDelete implements Command {

    /**
     * The player controller
     */
    private PlayerController playerController;

    /**
     * The team controller
     */
    private TeamController teamController;

    /**
     * Constructor of the class
     * @param playerController the player controller
     * @param teamController the team controller
     */
    public PlayerDelete(PlayerController playerController, TeamController teamController) {
        this.playerController = playerController;
        this.teamController = teamController;
    }

    /**
     * Deletes a player if he is not in a team with active tournaments and he is not in a tournament
     * @param playerName an array of strings with the parameters "player-delete playerName"
     * @return the output of the command
     */
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        String[] playerName = stringsep[1].split(";");
        Boolean isInATeam = false;
        if(playerName.length == 1) {
            for (Team team : teamController.getTeams()) {
                if (Boolean.TRUE.equals(team.isMember(playerController.search(playerName[0]))) && !team.getTournaments().isEmpty()) {
                        output = "The player " + playerName[0] + " is in a team with active tournaments, you must remove the player from the team or remove the team from the tournament before deleting it";
                        isInATeam = true;
                        break;
                    }
            }
            if (Boolean.FALSE.equals(isInATeam)) {
                output = playerController.deletePlayer(playerName[0]);
            }
        } else {
            output = "Incorrect number of parameters, only the name of the player is required";
        }
        return output;
    }

    /**
     * Returns a string with the description of the command
     * @return the description of the command
     */
    @Override
    public String toString() {
        return "> player-delete playerName";
    }

    /**
     * Returns the name of the command
     * @return the name of the command
     */
    @Override
    public String getCommand() {
        return "player-delete";
    }
}

